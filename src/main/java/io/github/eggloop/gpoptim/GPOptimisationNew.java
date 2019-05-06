package io.github.eggloop.gpoptim;

import io.github.eggloop.gaussianprocesses.gp.GpDataset;
import io.github.eggloop.gaussianprocesses.gp.GpPosterior;
import io.github.eggloop.gaussianprocesses.gp.HyperparamLogLikelihood;
import io.github.eggloop.gaussianprocesses.gp.kernels.Kernel;
import io.github.eggloop.gaussianprocesses.gp.regression.RegressionGP;
import io.github.eggloop.gpoptim.tranformations.EmptyTransformer;
import io.github.eggloop.gpoptim.tranformations.LogTransformer;
import io.github.eggloop.gpoptim.tranformations.Transformer;
import io.github.eggloop.numeric.algebra.linalg.IAlgebra;
import io.github.eggloop.numeric.algebra.linalg.JblasAlgebra;
import io.github.eggloop.numeric.algebra.linalg.NonPosDefMatrixException;
import io.github.eggloop.numeric.optimization.LocalOptimisation;
import io.github.eggloop.numeric.optimization.ObjectiveFunction;
import io.github.eggloop.numeric.optimization.PointValue;
import io.github.eggloop.numeric.optimization.methods.PowellMethodApache;

public class GPOptimisationNew {

    private GpoOptions options = new GpoOptions();
    private RegressionGP gp;
    private Transformer pointTransformer = null;
    final private IAlgebra algebra;

    public GPOptimisationNew() {
        this(new JblasAlgebra());
    }

    private GPOptimisationNew(IAlgebra algebra) {
        this.algebra = algebra;
    }

    public GpoOptions getOptions() {
        return options;
    }

    public void setOptions(GpoOptions options) {
        this.options = options;
    }


    public double[] optimise(ObjectiveFunction objFunction, double[] lbounds, double[] ubounds) {
        final Kernel kernel = options.getKernelGP();
        gp = new RegressionGP(algebra, kernel);
        gp.setPreferInversion(true);
        if (options.getLogspace()) {
            pointTransformer = new LogTransformer();
        } else {
            pointTransformer = new EmptyTransformer();
        }
        lbounds = pointTransformer.applyTransformation(lbounds);
        ubounds = pointTransformer.applyTransformation(ubounds);
        final int n = options.getInitialObservations();
        initialiseGP(n, lbounds, ubounds, objFunction);
        if (options.getHyperparamOptimisation()) {
            optimiseGPHyperParameters(options);
        } else if (options.useDefaultHyperparams()) {
            final GpDataset dataset = gp.getTrainingSet();
            final double[] hyp = kernel.getDefaultHyperparameters(dataset);
            kernel.setHyperarameters(hyp);
        }
        final int m = options.getGridSampleNumber();
        final int dim = lbounds.length;
        final GpDataset testSet = new GpDataset(dim, m);

        boolean notCoverged = true;
        int iteration = 0;
        int addedPointsNoImprovement = 0;
        int failedAttempts = 0;
        while (notCoverged) {
            if (iteration++ > options.getMaxIterations()) {
                notCoverged = false;
            } else if (addedPointsNoImprovement > options
                    .getMaxAddedPointsNoImprovement()) {
                notCoverged = false;
            } else if (failedAttempts > options.getMaxFailedAttempts()) {
                notCoverged = false;
            }

            final double beta = options.getBeta()
                    * (1 + 0.1 * Math.log(iteration));

            double[][] gridVals = options.getGridSampler().sample(m, lbounds,
                    ubounds);
            testSet.set(gridVals);
            GpPosterior gpPost;
            try {
                gpPost = gp.getGpPosterior(testSet);
            } catch (NonPosDefMatrixException e) {
                throw new IllegalStateException(e);
            }
            double[] decision = gpPost.getUpperBound(beta);
            double[] observations = gp.getTrainingSet().getTargets();

            int maxDecisionIndex = maxarg(decision);
            if (maxDecisionIndex == -1) {
                break;
            }
            double maxObservation = max(observations);
            double maxDecision;

            double[] candidate = new double[dim];
            System.arraycopy(gridVals[maxDecisionIndex], 0, candidate, 0, dim);
            maxDecision = optimiseCandidate(candidate, lbounds, ubounds, beta);
            //System.out.println(maxDecision);

            // found a new potential maximum
            if (maxDecision >= maxObservation) {
                failedAttempts = 0;
                final double lastObservation = this.addToGP(candidate,
                        objFunction);
                if (lastObservation < maxObservation
                        * options.getImprovementFactor())
                    addedPointsNoImprovement++;
                else
                    addedPointsNoImprovement = 0;
            } else
                failedAttempts++;
        }
//		//resetProgress("\n");

        int bestIndex = maxarg(gp.getTrainingSet().getTargets());
        if (bestIndex == -1) {
            bestIndex = 0;
        }
        double[] point = gp.getTrainingSet().getInstance(bestIndex);
        double fitness = gp.getTrainingSet().getTargets()[bestIndex];

        // optimise wrt the emulated function
        final double[] potential = point.clone();
        optimiseCandidate(potential, lbounds, ubounds, 0);
        final double potentialFit = objFunction.getValueAt(pointTransformer
                .invertTransformation(potential));
        if (potentialFit > fitness) {
            point = potential;
            fitness = potentialFit;
        }

        point = pointTransformer.invertTransformation(point);
        return point;
    }

    private void initialiseGP(int n, double[] lbounds, double[] ubounds,
                              ObjectiveFunction objFunction) {
        double[][] inputVals = options.getInitialSampler().sample(n, lbounds,
                ubounds);
        final double[] observations = new double[n];
        final double[] noise = new double[n];

        //resetProgress("Initial Evaluations...");
        for (int i = 0; i < n; i++) {
            final double[] point;
            point = pointTransformer.invertTransformation(inputVals[i]);
            observations[i] = objFunction.getValueAt(point);
        }

        GpDataset trainingSet = new GpDataset(inputVals, observations, noise);
        gp.setTrainingSet(trainingSet);
    }

    private double addToGP(double[] point, ObjectiveFunction objFunction) {
        point = pointTransformer.invertTransformation(point);
        final double observation = objFunction.getValueAt(point);
        final double noise = options.getNoiseTerm();
        point = pointTransformer.applyTransformation(point);
        gp.getTrainingSet().add(point, observation, noise);
        return observation;
    }

    private double optimiseCandidate(double[] candidate, double[] lbounds,
                                     double[] ubounds, double beta) {
        GPPosteriorQuantileFitness f = new GPPosteriorQuantileFitness(gp, beta);
        PointValue optimal = options.getLocalOptimiser().optimise(f, candidate);
        double[] optimalPoint = optimal.getPoint();
        double optimalValue = optimal.getValue();

        // If optimal is out of the specified search bounds, then discard.
        // (important, as a solution out-of-bounds could be nonsensical)
        boolean outOfBounds = false;
        for (int i = 0; i < lbounds.length; i++) {
            if (optimalPoint[i] < lbounds[i]) {
                optimalPoint[i] = lbounds[i];
                outOfBounds = true;
            } else if (optimalPoint[i] > ubounds[i]) {
                optimalPoint[i] = ubounds[i];
                outOfBounds = true;
            }
        }
        if (outOfBounds) {
            final double initialValue = f.getValueAt(candidate);
            optimalValue = f.getValueAt(optimalPoint);
            if (initialValue > optimalValue) {
                optimalPoint = candidate;
                optimalValue = initialValue;
            }
        }

        System.arraycopy(optimalPoint, 0, candidate, 0, candidate.length);
        return optimalValue;

    }

    private void optimiseGPHyperParameters(GpoOptions options) {
        final boolean logspace = true;
        HyperparamLogLikelihood func = new HyperparamLogLikelihood(gp, logspace);
        GpDataset train = gp.getTrainingSet();
        final double[] init = gp.getKernel().getDefaultHyperparameters(train);

        //     resetProgress("Hyperparameter optimisation...");
        //     System.out.println("HP:"+ Arrays.toString(init));
        if (logspace)
            for (int i = 0; i < init.length; i++)
                init[i] = Math.log(init[i]);

        LocalOptimisation alg = new PowellMethodApache();
        PointValue best = alg.optimise(func, init);
        for (int r = 0; r < options.getHyperparamOptimisationRestarts(); r++) {
            final double[] currentInit = new double[init.length];
            for (int i = 0; i < currentInit.length; i++)
                currentInit[i] = Math.random() * init[i] * 2;
            final PointValue curr = alg.optimise(func, currentInit);
            if (curr.getValue() > best.getValue())
                best = curr;
        }
        //     resetProgress("\n");

        final double[] point = best.getPoint();
        if (logspace)
            for (int i = 0; i < point.length; i++)
                point[i] = Math.exp(best.getPoint()[i]);
        //     System.out.println("HP: "+Arrays.toString(point));
        gp.getKernel().setHyperarameters(point);
    }

    static private double max(final double[] vector) {
        double max = Double.NEGATIVE_INFINITY;
        for (double v : vector)
            if (v > max)
                max = v;
        return max;
    }

    static private int maxarg(final double[] vector) {
        int maxIndex = -1;
        double max = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < vector.length; i++)
            if (vector[i] > max) {
                max = vector[i];
                maxIndex = i;
            }
        return maxIndex;
    }
}
