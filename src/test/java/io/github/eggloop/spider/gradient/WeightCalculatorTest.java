package io.github.eggloop.spider.gradient;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class WeightCalculatorTest {

    public static void main(String[] args) {
        Random random = new Random(1);
        int numberOfFormulae = 700;
        double[] weight = IntStream.range(0,numberOfFormulae).mapToDouble(s ->random.nextGaussian()/numberOfFormulae).toArray();
        boolean[] features = new boolean[weight.length];
        double[] rewards = new double[weight.length];

        for (int i = 0; i < features.length; i++) {
            features[i]=random.nextDouble()<0.5;
            //rewards[i]=10.0*(random.nextDouble()-0.5);
        }
        WeightCalculator.weightsUpdate(weight,features,0.1,1,8);
    }

   @Test
    void ciccio(){


   }


}