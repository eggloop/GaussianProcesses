package io.github.eggloop.numeric.algebra.matrix;

public class Matrix {

    public static double[] minMax(double[] array) {
        double max = Double.NEGATIVE_INFINITY;
        double min = Double.POSITIVE_INFINITY;
        for (double value : array) {
            if (value > max)
                max = value;
            else if (value < min)
                min = value;
        }
        return new double[]{min, max};

    }
}
