package io.github.eggloop.learning;

import io.github.eggloop.expr.Context;
import io.github.eggloop.gpoptim.GPOptimisationNew;
import io.github.eggloop.gpoptim.GpoOptions;
import io.github.eggloop.mitl.MiTL;
import io.github.eggloop.model.BooleanVerdict;
import io.github.eggloop.model.DoubleVerdict;
import io.github.eggloop.model.Trajectories;
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

class ComputeAverage2 {
    static double[] averageMultiTrajectory(int maxIterations, BiFunction<double[], double[], Double> fitness, String[] variablesUnique, Trajectories trainingSet, Formula formula, FormulaPopulation pop, double[] timeBoundsFormula, double atTime) throws InterruptedException {
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
            DoubleVerdict verdict = computeAverageRobustnessMultiTrajectory(trainingSet, variablesUnique, formula, point, atTime);
            double[] value1 = verdict.getStatsPositive();
            double[] value2 = verdict.getStatsNegative();
           // System.out.println(Arrays.toString(value1));
            double abs = fitness.apply(value1, value2);
           // System.out.println(abs);
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
        DoubleVerdict allClassification = getAllClassification(trainingSet, variablesUnique, formula, vv, atTime);
        ThresholdEvaluator th = new ThresholdEvaluator(allClassification.getPositive(), allClassification.getNegative());
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

    static DoubleVerdict computeAverageRobustnessMultiTrajectory(Trajectories trainingSet, String[] variables, Formula formula, double[] parametersValues, double atTime) {
        String[] parameterNames = formula.getParameters();
        Context ns = new Context(variables);
        MitlFactory factory = new MitlFactory(ns);
        String text = StringUtils.replace(formula.toString(), parameterNames, parametersValues) + "\n";
        MiTL prop = factory.constructProperty(text);
        return trainingSet.doubleVerdict(prop, atTime);
    }

    static DoubleVerdict getAllClassification(Trajectories trainingSet, String[] variables, Formula formula, double[] parametersValues, double atTime) throws InterruptedException {
        String[] parameterNames = formula.getParameters();
        Context ns = new Context(variables);
        MitlFactory factory = new MitlFactory(ns);
        String text = StringUtils.replace(formula.toString(), parameterNames, parametersValues) + "\n";
        MiTL prop = factory.constructProperty(text);
        return trainingSet.doubleVerdict(prop, atTime);
    }

    private static Callable<Double> callable(MiTL formula, Trajectory x, double time) {
        return () -> formula.clone().evaluateValue(x, time);
    }


    static BooleanVerdict smcMultiTrajectories(Trajectories trainingSet, String[] variables, Formula formula, double[] parametersValues, double atTime) {
        String[] parameterNames = formula.getParameters();
        Context ns = new Context(variables);
        MitlFactory factory = new MitlFactory(ns);
        String text = StringUtils.replace(formula.toString(), parameterNames, parametersValues) + "\n";
        MiTL prop = factory.constructProperty(text);
        BooleanVerdict verdict = trainingSet.booleanVerdict(prop, atTime);
        return verdict;
    }
}