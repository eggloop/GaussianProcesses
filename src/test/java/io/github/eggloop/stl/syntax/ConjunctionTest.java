package io.github.eggloop.stl.syntax;

import io.github.eggloop.expression.arithmetic.Constant;
import io.github.eggloop.expression.arithmetic.Variable;
import io.github.eggloop.expression.relational.Greater;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConjunctionTest {

    @Test
    void toStringTest() {
        STL left = new Atom(new Greater(new Variable("X"),new Constant(0)));
        STL right = new Atom(new Greater(new Variable("X"),new Variable("Y")));

        STL conjunction = new Conjunction(left,right);

        System.out.println(conjunction.toString());
    }
}