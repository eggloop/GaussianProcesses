package io.github.eggloop.gaussianprocesses.gp.kernels;

import io.github.eggloop.gaussianprocesses.gp.GpDataset;

public interface Kernel {

    double distance(double[] x1, double[] x2);

    double distanceDerivative(double[] x1,
                              double[] x2, int derivativeI);

    double distanceSecondDerivative(double[] x1,
                                    double[] x2, int derivativeI, int derivativeJ);

    double calculateHyperparamDerivative(double[] x1,
                                         double[] x2, int hyperparamIndex);

    double[] getHypeparameters();

    double[] getDefaultHyperparameters(GpDataset data);

    void setHyperarameters(double[] hyp);
}