package io.github.eggloop.model;

import java.util.Arrays;

public class BooleanVerdict {

    boolean[] positive;
    boolean[] negative;

    public BooleanVerdict(boolean[] positive, boolean[] negative) {
        this.positive = positive;
        this.negative = negative;
    }

    public double[] getStatsPositive() {
        double[] b = toDouble(positive);
        double mean = Arrays.stream(b).sum() / b.length;
        double variance = Math.sqrt(Arrays.stream(b).map(x -> (x - mean) * (x - mean)).sum() / b.length);
        return new double[]{mean, variance};
    }

    public double[] getStatsNegative() {
        double[] b = toDouble(negative);
        double mean = Arrays.stream(b).sum() / b.length;
        double variance = Math.sqrt(Arrays.stream(b).map(x -> (x - mean) * (x - mean)).sum() / b.length);
        return new double[]{mean, variance};
    }

    private double[] toDouble(boolean[] array) {
        double[] res = new double[array.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = array[i] ? 1 : 0;

        }
        return res;

    }
}
