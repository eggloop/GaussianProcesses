package io.github.eggloop.expression.relational;

import io.github.eggloop.expression.arithmetic.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GreaterThanTest {

    private RelationalExpression greaterThan;

    @BeforeEach
    void setUp() {
        ArithmeticExpression left = new Variable("X");
        ArithmeticExpression right = new Constant(0);
        greaterThan = new GreaterThan(left, right);
    }

    @Test
    void testEvaluate() {
        Assignment assignment = new Assignment();
        assignment.put("X", 1.);
        BooleanDomain domain = new BooleanDomain();

        DomainFunction<Boolean> compiledEqualTo = greaterThan.compile(domain);

        assertTrue(compiledEqualTo.evaluate(assignment));
    }

    @Test
    void testPrint() {
        String expected = "(X > 0.0)";

        assertEquals(expected, greaterThan.print().evaluate(new Assignment()));
    }

    @Test
    void testToString() {
        String expected = "(X > 0.0)";

        assertEquals(expected, greaterThan.toString());
    }
}