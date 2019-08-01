package io.github.eggloop.spider;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ParameterSupplierTest {

    @Test
    void get() {
        List<double[]> time = new ArrayList<>();
        time.add(new double[]{1,2});
        time.add(new double[]{4,5});

        List<double[]> space = new ArrayList<>();
        space.add(new double[]{1,2});
        space.add(new double[]{2,5});

        ParameterSupplier param = new ParameterSupplier(time,space);
        param.setConfiguration(2,1);
//        for (int i = 0; i < param.length()-1; i++) {
//            System.out.println(Arrays.toString(param.get()));
//
//        }
        Stream.generate(param).limit(param.length()).map(Arrays::toString).forEach(System.out::println);
//        System.out.println(Arrays.toString(param.get()));
//        System.out.println(Arrays.toString(param.get()));
//        System.out.println(Arrays.toString(param.get()));

    }
}