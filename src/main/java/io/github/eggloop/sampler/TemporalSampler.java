package io.github.eggloop.sampler;

import io.github.eggloop.utils.numeric.RandomUtils;

import java.util.Random;

public class TemporalSampler implements GridSampler {

    private Random random;
    private int temporalIntervals;

    public TemporalSampler(int seed, int temporalIntervals) {
        this.random = RandomUtils.getRandom(seed);
        this.temporalIntervals = temporalIntervals;
    }

    @Override
    public double[][] sample(int n, double[] lb, double[] ub) {
        double[][] res = new double[n][lb.length];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < temporalIntervals; j += 2) {
                res[i][j] = lb[j] + random.nextDouble() * (ub[j] - lb[j]);
                res[i][j + 1] = res[i][j] + (random.nextDouble()) * (ub[j] - res[i][j]);
            }
            for (int j = temporalIntervals; j < res[i].length; j++) {
                res[i][j] = lb[j] + random.nextDouble() * (ub[j] - lb[j]);
            }
        }
        return res;
    }

    @Override
    public double[][] sample(int n, Parameter[] params) {
        return new double[0][];
    }
}