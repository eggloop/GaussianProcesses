package io.github.eggloop.learning;

import io.github.eggloop.expr.Context;
import io.github.eggloop.gpoptimisation.gpoptim.GPOptimisationNew;
import io.github.eggloop.gpoptimisation.gpoptim.GpoOptions;
import io.github.eggloop.mitl.MiTL;
import io.github.eggloop.model.Trajectory;
import io.github.eggloop.numeric.optimization.ObjectiveFunction;
import io.github.eggloop.parsers.MitlFactory;
import io.github.eggloop.simhya.simhya.matlab.genetic.Formula;
import io.github.eggloop.simhya.simhya.matlab.genetic.FormulaPopulation;
import io.github.eggloop.utils.classifier.ThresholdEvaluator;
import io.github.eggloop.utils.string.StringUtils;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.stream.IntStream;

class ComputeAverageNew {
    static double[] averageMultiTrajectory(int maxIterations, BiFunction<double[], double[], Double> fitness, String[] variablesUnique, double[] ds2Times, double[][][] normal_model, double[][][] ineffective_model, Formula formula, FormulaPopulation pop, double[] timeBoundsFormula, double atTime) throws InterruptedException {
        String[] variables = formula.getVariables();
        String[] boundsFormula = formula.getTimeBounds();
        double[] timeBoundsLb = Arrays.stream(boundsFormula).mapToDouble(x -> timeBoundsFormula[0]).toArray();
        double[] timeBoundsUb = Arrays.stream(boundsFormula).mapToDouble(x -> timeBoundsFormula[1]).toArray();
        double[] thrshldLb = Arrays.stream(variables).mapToDouble(pop::getLowerBound).toArray();
        double[] thrshldUb = Arrays.stream(variables).mapToDouble(pop::getUpperBound).toArray();
        double[] lb = new double[boundsFormula.length + variables.length];
        double[] ub = new double[boundsFormula.length + variables.length];
        System.arraycopy(timeBoundsLb, 0, lb, 0, timeBoundsLb.length);
        System.arraycopy(thrshldLb, 0, lb, boundsFormula.length, thrshldLb.length);
        System.arraycopy(timeBoundsUb, 0, ub, 0, timeBoundsUb.length);
        System.arraycopy(thrshldUb, 0, ub, boundsFormula.length, thrshldUb.length);
        ObjectiveFunction function = point -> {
            for (int i = 0; i < boundsFormula.length - 1; i += 2) {
                point[i + 1] = point[i] + point[i + 1] * (1 - point[i]);
            }
            final double[] p = point;
            point = IntStream.range(0, point.length).mapToDouble(i -> lb[i] + p[i] * (ub[i] - lb[i])).toArray();
            double[] value1 = computeAverageRobustnessMultiTrajectory(ds2Times, normal_model, variablesUnique, formula, point, atTime);
            double[] value2 = computeAverageRobustnessMultiTrajectory(ds2Times, ineffective_model, variablesUnique, formula, point, atTime);
            double abs = fitness.apply(value1, value2);
            if (Double.isNaN(abs)) {
                return 0;
            }
            //System.out.println(abs);
            return abs;
        };
        //GridSampler custom = new TemporalSampler(4, boundsFormula.length);
        GPOptimisationNew gpo = new GPOptimisationNew();
        GpoOptions options = new GpoOptions();
        //options.setInitialSampler(custom);
        options.setMaxIterations(maxIterations);
        options.setHyperparamOptimisation(true);
        //options.setGridSampler(custom);
        //options.setGridSampleNumber(400);
        options.setInitialObservations(50);
        options.setHyperparamOptimisationRestarts(100);
        gpo.setOptions(options);
        double[] lbU = IntStream.range(0, lb.length).mapToDouble(i -> 0).toArray();
        double[] ubU = IntStream.range(0, ub.length).mapToDouble(i -> 1).toArray();
        double[] v = gpo.optimise(function, lbU, ubU);
        double[] vv = IntStream.range(0, v.length).mapToDouble(i -> lb[i] + v[i] * (ub[i] - lb[i])).toArray();
        //     double[] p1u1 = computeAverageRobustnessMultiTrajectory(ds2Times, normal_model, variablesUnique, formula, vv, atTime);
        //    double[] p2u2 = computeAverageRobustnessMultiTrajectory(ds2Times, ineffective_model, variablesUnique, formula, vv, atTime);
        double[] positive = getAllClassification(ds2Times, normal_model, variablesUnique, formula, vv, atTime);
        double[] negative = getAllClassification(ds2Times, ineffective_model, variablesUnique, formula, vv, atTime);
        ThresholdEvaluator th = new ThresholdEvaluator(positive, negative);
        double value = th.getThreshold();


//        double value;
//        if (p1u1[0] > p2u2[0]) {
//            value = ((p1u1[0] - p1u1[1]) + (p2u2[0] + p2u2[1])) / 2;
//        } else {
//            value = ((p2u2[0] - p2u2[1]) + (p1u1[0] + p1u1[1])) / 2;
//        }
        char[] a = formula.toSign().toCharArray();
        for (int i = timeBoundsLb.length; i < vv.length; i++) {
            if (a[i - timeBoundsLb.length] == '1') {
                vv[i] = Math.max(vv[i] + value, 0);
            } else {
                vv[i] = Math.max(vv[i] - value, 0);
            }
        }
        return vv;
    }

    static double[] computeAverageRobustnessMultiTrajectory(double[] times, double[][][] simulate, String[] variables, Formula formula, double[] parametersValues, double atTime) {
        double[] b = new double[simulate.length];
        String[] parameterNames = formula.getParameters();
        Context ns = new Context(variables);
        MitlFactory factory = new MitlFactory(ns);
        String text = StringUtils.replace(formula.toString(), parameterNames, parametersValues) + "\n";
        MiTL prop = factory.constructProperty(text);
        for (int i = 0; i < simulate.length; i++) {
            Trajectory x = new Trajectory(times, ns, simulate[i]);
            b[i] = prop.evaluateValue(x, atTime);
        }
        double mean = Arrays.stream(b).sum() / b.length;
        double variance = Math.sqrt(Arrays.stream(b).map(x -> (x - mean) * (x - mean)).sum() / b.length);
        return new double[]{mean, variance};
    }

    static double[] getAllClassification(double[] times, double[][][] simulate, String[] variables, Formula formula, double[] parametersValues, double atTime) throws InterruptedException {
        double[] b = new double[simulate.length];
        String[] parameterNames = formula.getParameters();
        Context ns = new Context(variables);
        MitlFactory factory = new MitlFactory(ns);
        String text = StringUtils.replace(formula.toString(), parameterNames, parametersValues) + "\n";
        MiTL prop = factory.constructProperty(text);

//        ExecutorService executor = Executors.newWorkStealingPool();
//
//        List<Callable<Double>> callables = new ArrayList<>();
//        for (double[][] doubles : simulate) {
//            Trajectory x = new Trajectory(times, ns, doubles);
//            callables.add(callable(prop, x, atTime));
//        }
//
//        double[] objects = executor.invokeAll(callables)
//                .stream()
//                .mapToDouble(future -> {
//                    try {
//                        return future.get();
//                    } catch (Exception e) {
//                        throw new IllegalStateException(e);
//                    }
//                })
//                .toArray();
//        return objects;


        for (int i = 0; i < simulate.length; i++) {
            Trajectory x = new Trajectory(times, ns, simulate[i]);
            b[i] = prop.evaluateValue(x, atTime);
        }
        return b;
    }

    private static Callable<Double> callable(MiTL formula, Trajectory x, double time) {
        return () -> formula.clone().evaluateValue(x, time);
    }


    static double[] smcMultiTrajectories(double[] times, double[][][] trajectories, String[] variables, Formula formula, double[] parametersValues, double atTime) {
        double[] b = new double[trajectories.length];
        String[] parameterNames = formula.getParameters();
        Context ns = new Context(variables);
        MitlFactory factory = new MitlFactory(ns);
        String text = StringUtils.replace(formula.toString(), parameterNames, parametersValues) + "\n";
        MiTL prop = factory.constructProperty(text);

        for (int i = 0; i < trajectories.length; i++) {
            Trajectory x = new Trajectory(times, ns, trajectories[i]);
            b[i] = prop.evaluate(x, atTime) ? 1 : 0;
        }
        double mean = Arrays.stream(b).sum() / b.length;
        double variance = Arrays.stream(b).map(x -> (x - mean) * (x - mean)).sum() / b.length;
        return new double[]{mean, variance};
    }
}