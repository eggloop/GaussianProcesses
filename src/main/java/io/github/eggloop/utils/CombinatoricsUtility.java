package io.github.eggloop.utils;

import java.util.ArrayList;
import java.util.List;

public class CombinatoricsUtility {

    public CombinatoricsUtility() {
        //Utility Class
    }

    public static List<double[]> generateIntervals(double[] array) {
        List<double[]> intervals = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            double left = array[i];
            for (int j = i + 1; j < array.length; j++) {
                intervals.add(new double[]{left, array[j]});
            }
        }
        return intervals;
    }

}
