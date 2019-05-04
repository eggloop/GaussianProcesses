package io.github.eggloop.model;

import java.util.Arrays;

public class DoubleVerdict {

    double[] positive;
    double[] negative;

    public DoubleVerdict(double[] positive, double[] negative) {
        this.positive = positive;
        this.negative = negative;
    }

    public double[] getPositive() {
        return positive;
    }

    public double[] getNegative() {
        return negative;
    }

    public double[] getStatsPositive() {
        double[] b = positive;
        double mean = Arrays.stream(b).sum() / b.length;
        double variance = Math.sqrt(Arrays.stream(b).map(x -> (x - mean) * (x - mean)).sum() / b.length);
        return new double[]{mean, variance};
    }

    public double[] getStatsNegative() {
        double[] b = negative;
        double mean = Arrays.stream(b).sum() / b.length;
        double variance = Math.sqrt(Arrays.stream(b).map(x -> (x - mean) * (x - mean)).sum() / b.length);
        return new double[]{mean, variance};
    }


}
