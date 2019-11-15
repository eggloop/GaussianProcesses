package io.github.eggloop.spider.gradient;

import io.github.eggloop.expression.arithmetic.Assignment;
import io.github.eggloop.expression.arithmetic.Variable;
import io.github.eggloop.expression.relational.GreaterEqualTo;
import io.github.eggloop.expression.relational.LowerEqualTo;
import io.github.eggloop.spider.Bean;
import io.github.eggloop.stl.syntax.*;
import io.github.eggloop.stl.visitor.BooleanTemporalMonitoring;
import io.github.eggloop.trajectories.Trajectory;
import me.tongfei.progressbar.ProgressBar;

import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class WeightCalculator {

    private double alpha;
    private double lambda;
    private Bean bean;

    public WeightCalculator(Bean bean, double alpha, double lambda) {
        this.alpha = alpha;
        this.lambda = lambda;
        this.bean = bean;
    }

    public double[] updateAll(double[] rewards, Trajectory[] trajectories) {
        Random random = new Random(1);
        int totalFormulae = bean.getEventually().size() + bean.getGlobally().size();
        double std = 1.0 / totalFormulae;
        double[] weights = IntStream.range(0, totalFormulae).mapToDouble(s -> std * random.nextGaussian()).toArray();
        //X>=h
        Formula atomh = new Atom(new GreaterEqualTo(new Variable("X"), new Variable("h")));
        //X<=l
        Formula atoml = new Atom(new LowerEqualTo(new Variable("X"), new Variable("l"))); //X<=l
        //F[a,b] (X>=h and X<=l)
        Formula aFinally = new Finally(new Interval(new Variable("a"), new Variable("b")), new Conjunction(atomh, atoml));
        //G[a,b] (X>=h and X<=l)
        Formula aGlobally = new Globally(new Interval(new Variable("a"), new Variable("b")), new Conjunction(atomh, atoml));

        Function<double[], Assignment> ass = value -> {
            Assignment assignment = new Assignment();
            assignment.put("h", value[0]);
            assignment.put("l", value[1]);
            assignment.put("a", value[2]);
            assignment.put("b", value[3]);
            return assignment;
        };
        Function<BooleanTemporalMonitoring, Predicate<double[]>> checkFinally = getCheck(aFinally, ass);
        Function<BooleanTemporalMonitoring, Predicate<double[]>> checkGlobally = getCheck(aGlobally, ass);
        try (ProgressBar pb = new ProgressBar("Weight Generation", trajectories.length)) {
            for (int i = 0; i < trajectories.length; i++) {
//                System.out.println("--");
                BooleanTemporalMonitoring booleanTemporalMonitoring = new BooleanTemporalMonitoring(trajectories[i]);
                Predicate<double[]> finallyCheck = checkFinally.apply(booleanTemporalMonitoring);
                Predicate<double[]> globallyCheck = checkGlobally.apply(booleanTemporalMonitoring);
                boolean[] features = eval(bean, finallyCheck, globallyCheck);
                weights = weightsUpdate(weights, features, alpha, lambda, rewards[i]);
                pb.step();
            }
            return weights;
        }
    }


    private static double scalarProduct(boolean[] features, double[] oldWeights) {
        return IntStream.range(0, features.length).mapToDouble(index -> features[index] ? oldWeights[index] : 0.0).reduce(0.0, Double::sum);
    }

    private static double weightUpdate(double update, double oldWeight, double alpha, double lambda) {
            //double update = feature ? alpha * (r - scalarProduct) : 0;
            double weight = oldWeight + update;
            if (weight > 0) {
                weight = weight - alpha * lambda;
            } else if (weight < 0) {
                weight = weight + alpha * lambda;
            }
            return weight;
    }

    public static double[] weightsUpdate(double[] oldWeights, boolean[] features, double alpha, double lambda, double r) {
        double scalarProduct = scalarProduct(features, oldWeights);
        double update = alpha * (r - scalarProduct);
        double[] weights = new double[oldWeights.length];
        for (int i = 0; i < weights.length; i++) {
            double updateIfFeature = features[i] ? update : 0;
            weights[i] = weightUpdate(updateIfFeature, oldWeights[i], alpha, lambda);
        }
        return weights;
    }

    private Function<BooleanTemporalMonitoring, Predicate<double[]>> getCheck(Formula formula, Function<double[], Assignment> ass) {
        return booleanTemporalMonitoring -> construct(formula, booleanTemporalMonitoring, ass);
    }

    private static Predicate<double[]> construct(Formula formula, BooleanTemporalMonitoring monitor, Function<
            double[], Assignment> assignment) {
        return value -> formula.accept(monitor).evaluate(assignment.apply(value));
    }

    private boolean[] eval(Bean bean, Predicate<double[]> finallyCheck, Predicate<double[]> globallyCheck) {
        int eventuallySize = bean.getEventually().size();
        boolean[] result = new boolean[bean.getGlobally().size() + eventuallySize];
        for (int i = 0; i < eventuallySize; i++) {
            result[i] = finallyCheck.test(bean.getEventually().get(i));
        }
        for (int i = eventuallySize; i < result.length; i++) {
            result[i] = globallyCheck.test(bean.getGlobally().get(i - eventuallySize));
        }
        return result;
    }

}