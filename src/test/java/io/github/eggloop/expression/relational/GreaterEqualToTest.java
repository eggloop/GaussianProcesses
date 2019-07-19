package io.github.eggloop.expression.relational;

import io.github.eggloop.expression.arithmetic.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GreaterEqualToTest {

    private RelationalExpression greaterEqualTo;

    @BeforeEach
    void setUp() {
        ArithmeticExpression left = new Variable("X");
        ArithmeticExpression right = new Constant(0);
        greaterEqualTo = new GreaterEqualTo(left, right);
    }

    @Test
    void evaluateTest() {
        Assignment assignment = new Assignment();
        assignment.put("X", 1.);
        BooleanDomain domain = new BooleanDomain();

        DomainFunction<Boolean> compiledEqualTo = greaterEqualTo.compile(domain);

        assertTrue(compiledEqualTo.evaluate(assignment));
    }

    @Test
    void toStringTest() {
        String expected = "X >= 0.0";

        assertEquals(expected, greaterEqualTo.print().evaluate(new Assignment()));
    }

}