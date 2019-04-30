package io.github.eggloop.gaussianprocesses.gp.kernels;

import io.github.eggloop.gaussianprocesses.gp.GpDataset;
import io.github.eggloop.numeric.algebra.matrix.Matrix;

public class RBF implements Kernel {

    private double[] hyp;
    private double amplitude2;
    private double invLengthscale2;

    public RBF() {
        this(1, 1);
    }

    public RBF(double a, double l) {
        setHyperarameters(new double[]{a, l});
    }

    @Override
    public void setHyperarameters(double[] hyp) {
        this.hyp = hyp;
        final double a = hyp[0];
        final double l = hyp[1];
        this.amplitude2 = a * a;
        this.invLengthscale2 = 1.0 / (l * l);
    }

    @Override
    public double[] getHypeparameters() {
        return hyp;
    }

    @Override
    public double[] getDefaultHyperparameters(GpDataset data) {
        final double[] defaultHyp = new double[hyp.length];
        double[] minMax = Matrix.minMax(data.getTargets());
        double min = minMax[0];
        double max = minMax[1];
        defaultHyp[0] = Double.compare(max, min) == 0 ? 1 : (max - min) / 2.0;
        double sum = 0;
        final int n = data.getSize();
        final int dim = data.getDimension();
        for (int d = 0; d < dim; d++) {
            max = Double.NEGATIVE_INFINITY;
            min = Double.POSITIVE_INFINITY;
            for (int i = 0; i < n; i++) {
                final double curr = data.getInstance(i)[d];
                if (curr > max)
                    max = curr;
                if (curr < min)
                    min = curr;
            }
            sum += (max - min) / 10.0;
        }
        defaultHyp[1] = sum / dim;

        return defaultHyp;
    }

    @Override
    public double distance(double[] x1, double[] x2) {
        final int n = x1.length;
        double sum = 0;
        for (int i = 0; i < n; i++) {
            final double v = x1[i] - x2[i];
            sum += v * v;
        }
        // exp(-0.5 * sum((x1 - x2).^2));
        return amplitude2 * Math.exp(-0.5 * sum * invLengthscale2);
    }

    @Override
    public double distanceDerivative(double[] x1, double[] x2, int i) {
        // dfxdx = -(x / l^2) * fx(x);
        return -invLengthscale2 * (x1[i] - x2[i]) * distance(x1, x2);
    }

    @Override
    public double distanceSecondDerivative(double[] x1, double[] x2, int i,
                                           int j) {
        final double k0 = distance(x1, x2);
        final double ki = 2 * invLengthscale2 * (x1[i] - x2[i]);
        final double kj = 2 * invLengthscale2 * (x1[j] - x2[j]);
        double k = k0 * ki * kj;
        if (i == j)
            k = k - 2 * invLengthscale2 * k0;
        return k;
    }

    @Override
    public double calculateHyperparamDerivative(double[] x1, double[] x2, int hyperparamIndex) {
        final int n = x1.length;
        double sum = 0;
        for (int i = 0; i < n; i++) {
            final double v = x1[i] - x2[i];
            sum += v * v;
        }

        // df(a)/da = 2 * a * exp(-0.5 * x^2 / l^2);
        if (hyperparamIndex == 0)
            return 2 * hyp[0] * Math.exp(-0.5 * sum * invLengthscale2);

        // df(l)/dl = (x^2 / l^3) * f(l);
        if (hyperparamIndex == 1)
            return distance(x1, x2) * sum * invLengthscale2 / hyp[1];

        throw new IllegalStateException();
    }

}
