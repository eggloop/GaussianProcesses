package io.github.eggloop.expression.relational;

import io.github.eggloop.expression.arithmetic.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LowerThanTest {
    private RelationalExpression lowerThan;

    @BeforeEach
    void setUp() {
        ArithmeticExpression left = new Variable("X");
        ArithmeticExpression right = new Constant(0);
        lowerThan = new LowerThan(left, right);
    }

    @Test
    void evaluateTest() {
        Assignment assignment = new Assignment();
        assignment.put("X", -2.);
        BooleanDomain domain = new BooleanDomain();

        DomainFunction<Boolean> compiledEqualTo = lowerThan.compile(domain);

        assertTrue(compiledEqualTo.evaluate(assignment));
    }

    @Test
    void toStringTest() {
        String expected = "X < 0.0";

        assertEquals(expected, lowerThan.print().evaluate(new Assignment()));
    }


}