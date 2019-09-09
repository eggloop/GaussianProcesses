package io.github.eggloop.spider;

import io.github.eggloop.expression.arithmetic.Constant;
import io.github.eggloop.expression.arithmetic.Variable;
import io.github.eggloop.expression.relational.GreaterEqualTo;
import io.github.eggloop.stl.syntax.Atom;
import io.github.eggloop.stl.syntax.Formula;
import io.github.eggloop.utils.Serialization;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BeanBestTest {

    @Test
    void put() {
    }

    @Test
    void getStorage() {
    }

    @Test
    void name(@TempDir Path tempDir) throws IOException, ClassNotFoundException {
        String filePath = tempDir.resolve("file").toString();
        BeanBest beanBest = new BeanBest();
        Formula formula = new Atom(new GreaterEqualTo(new Variable("X"),new Constant(2)));
        beanBest.put(formula, Collections.singletonList(new double[]{1, 2, 3}));

        Serialization.serialize(beanBest,filePath);
        BeanBest deSerialized = (BeanBest) Serialization.deSerialize(filePath);

        Map<Formula, List<double[]>> storage = deSerialized.getStorage();
        System.out.println();
    }
}