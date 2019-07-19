package io.github.eggloop.utils;

import java.util.OptionalInt;
import java.util.stream.IntStream;

public class ArraysUtility {

    private ArraysUtility() {
        //Utility class
    }

    public static IntStream getPositionOfOrderedArrayBetween(double[] array, double left, double right) {
        int last = array.length;
        int first = last - 1;
        OptionalInt optionalFirst = IntStream.range(0, last).filter(i -> array[i] > left && i > 0).findFirst();
        if (optionalFirst.isPresent()) {
            first = optionalFirst.getAsInt() - 1;
        }
        OptionalInt optionalLast = IntStream.range(0, last).filter(i -> array[i] > right).findFirst();
        if (optionalLast.isPresent()) {
            last = optionalLast.getAsInt();
        }
        return IntStream.range(first, last);
    }
}