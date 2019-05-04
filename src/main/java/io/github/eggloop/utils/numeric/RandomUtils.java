package io.github.eggloop.utils.numeric;

import java.util.Random;

public class RandomUtils {

    public static Random getRandom(int seed) {
        return seed == 0 ? new Random() : new Random(seed);
    }
}