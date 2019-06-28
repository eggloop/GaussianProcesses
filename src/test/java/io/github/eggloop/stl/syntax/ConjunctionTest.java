package io.github.eggloop.stl.syntax;

import io.github.eggloop.expression.arithmetic.Constant;
import io.github.eggloop.expression.arithmetic.Variable;
import io.github.eggloop.expression.relational.GreaterThan;
import org.junit.jupiter.api.Test;

class ConjunctionTest {

    @Test
    void toStringTest() {
        STL left = new Atom(new GreaterThan(new Variable("X"),new Constant(0)));
        STL right = new Atom(new GreaterThan(new Variable("X"),new Variable("Y")));

        STL conjunction = new Conjunction(left,right);

        System.out.println(conjunction.toString());
    }
}