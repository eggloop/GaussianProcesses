package io.github.eggloop.common;

import io.github.eggloop.expression.arithmetic.Constant;
import io.github.eggloop.expression.arithmetic.Variable;
import io.github.eggloop.expression.relational.GreaterThan;
import io.github.eggloop.stl.syntax.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StringHashCodeTest {

    @Test
    void testStringHashCodeOnFinally() {
        Interval interval = new Interval(new Constant(2), new Constant(3));
        Formula atom = new Atom(new GreaterThan(new Variable("X"), new Constant(2)));

        StringHashCode eventually = new Finally(interval, atom);

        Assertions.assertEquals("F_(X > 2.0)", eventually.hash());
    }

    @Test
    void testStringHashCodeOnGlobally() {
        Interval interval = new Interval(new Constant(2), new Constant(3));
        Formula atom = new Atom(new GreaterThan(new Variable("X"), new Constant(2)));

        StringHashCode globally = new Globally(interval, atom);

        Assertions.assertEquals("G_(X > 2.0)", globally.hash());
    }
}