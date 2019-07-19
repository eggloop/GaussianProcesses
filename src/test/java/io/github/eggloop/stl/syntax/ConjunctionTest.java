package io.github.eggloop.stl.syntax;

import io.github.eggloop.expression.arithmetic.Constant;
import io.github.eggloop.expression.arithmetic.Variable;
import io.github.eggloop.expression.relational.GreaterThan;
import org.junit.jupiter.api.Test;

class ConjunctionTest {

    @Test
    void toStringTest() {
        Formula left = new Atom(new GreaterThan(new Variable("X"), new Constant(0)));
        Formula right = new Atom(new GreaterThan(new Variable("X"), new Variable("Y")));

        Formula conjunction = new Conjunction(left, right);

        System.out.println(conjunction.toString());
    }
}