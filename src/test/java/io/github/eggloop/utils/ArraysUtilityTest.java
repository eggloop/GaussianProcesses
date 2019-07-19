package io.github.eggloop.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

class ArraysUtilityTest {

    @Test
    void testGetPositionOfOrderedArrayBetween() {
        double[] times = new double[]{0.4, 0.7, 1.3, 5.6, 7.4, 10.3};

        IntStream positionOfOrderedArrayBetween = ArraysUtility.getPositionOfOrderedArrayBetween(times, 3.2, 7.45);
        int[] filteredPositions = positionOfOrderedArrayBetween.toArray();

        Assertions.assertArrayEquals(new int[]{2, 3, 4}, filteredPositions);
    }

    @Test
    void testGetPositionOfOrderedArrayBetween2() {
        double[] times = new double[]{0.4, 0.7, 1.3, 5.6, 7.4, 10.3};

        IntStream positionOfOrderedArrayBetween = ArraysUtility.getPositionOfOrderedArrayBetween(times, 0.0, 7.45);
        int[] filteredPositions = positionOfOrderedArrayBetween.toArray();

        Assertions.assertArrayEquals(new int[]{0, 1, 2, 3, 4}, filteredPositions);
    }

    @Test
    void testGetPositionOfOrderedArrayBetween3() {
        double[] times = new double[]{0.4, 0.7, 1.3, 5.6, 7.4, 10.3};

        IntStream positionOfOrderedArrayBetween = ArraysUtility.getPositionOfOrderedArrayBetween(times, 0.0, 120);
        int[] filteredPositions = positionOfOrderedArrayBetween.toArray();

        Assertions.assertArrayEquals(new int[]{0, 1, 2, 3, 4, 5}, filteredPositions);
    }

    @Test
    void testGetPositionOfOrderedArrayBetween4() {
        double[] times = new double[]{0.4, 0.7, 1.3, 5.6, 7.4, 10.3};

        IntStream positionOfOrderedArrayBetween = ArraysUtility.getPositionOfOrderedArrayBetween(times, 5.9, 120);
        int[] filteredPositions = positionOfOrderedArrayBetween.toArray();

        Assertions.assertArrayEquals(new int[]{3, 4, 5}, filteredPositions);
    }

    @Test
    void testGetPositionOfOrderedArrayBetween5() {
        double[] times = new double[]{0.4, 0.7, 1.3, 5.6, 7.4, 10.3};

        IntStream positionOfOrderedArrayBetween = ArraysUtility.getPositionOfOrderedArrayBetween(times, 5.9, 6.3);
        int[] filteredPositions = positionOfOrderedArrayBetween.toArray();

        Assertions.assertArrayEquals(new int[]{3}, filteredPositions);
    }

    @Test
    void testGetPositionOfOrderedArrayBetween6() {
        double[] times = new double[]{0.4, 0.7, 1.3, 5.6, 7.4, 10.3};

        IntStream positionOfOrderedArrayBetween = ArraysUtility.getPositionOfOrderedArrayBetween(times, 0, 0.2);
        int[] filteredPositions = positionOfOrderedArrayBetween.toArray();

        Assertions.assertArrayEquals(new int[]{}, filteredPositions);
    }

    @Test
    void testGetPositionOfOrderedArrayBetween7() {
        double[] times = new double[]{0.4, 0.7, 1.3, 5.6, 7.4, 10.3};

        IntStream positionOfOrderedArrayBetween = ArraysUtility.getPositionOfOrderedArrayBetween(times, 10.4, 120);
        int[] filteredPositions = positionOfOrderedArrayBetween.toArray();

        Assertions.assertArrayEquals(new int[]{5}, filteredPositions);
    }

    @Test
    void testGetPositionOfOrderedArrayBetween8() {
        double[] times = new double[]{0.4, 0.7, 1.3, 5.6, 7.4, 10.3};

        IntStream positionOfOrderedArrayBetween = ArraysUtility.getPositionOfOrderedArrayBetween(times, 10.2, 120);
        int[] filteredPositions = positionOfOrderedArrayBetween.toArray();

        Assertions.assertArrayEquals(new int[]{4, 5}, filteredPositions);
    }
}