package io.github.eggloop.utils.classifier;

import java.util.Arrays;

public class ThresholdEvaluator {

    private final double[] positiveClass;
    private final double[] negativeClass;

    public ThresholdEvaluator(double[] positiveClass, double[] negativeClass) {

        this.positiveClass = positiveClass;
        this.negativeClass = negativeClass;
    }

    public double getThreshold() {
        Arrays.sort(positiveClass);
        Arrays.sort(negativeClass);

        if (positiveClass[0] > negativeClass[negativeClass.length - 1]) {
            return (positiveClass[0] + negativeClass[negativeClass.length - 1]) / 2;
        } else {
            int i = findPointLessThan(positiveClass[0], negativeClass);
            i = Math.min(Math.max(0, i), negativeClass.length - 1);
            int posK = 1;
            int negK = i;
            int bestBad = negativeClass.length - i;
            int bad = negativeClass.length - i;
            while (negK < negativeClass.length - 1 && posK < positiveClass.length - 1) {
                int compare = Double.compare(positiveClass[posK], negativeClass[negK]);
                if (compare < 0) {
                    posK++;
                    bad++;
                } else if (compare > 0) {
                    negK++;
                    bad--;
                    if (bad < bestBad) {
                        bestBad = bad;
                    }
                } else {
                    posK++;
                    negK++;
                }
            }
            return negativeClass[negK];
        }
    }


    private int findPointLessThan(double value, double[] vector) {
        for (int i = 0; i < vector.length; i++) {
            if (vector[i] > value) {
                return i;
            }
        }
        return vector.length;
    }
}
