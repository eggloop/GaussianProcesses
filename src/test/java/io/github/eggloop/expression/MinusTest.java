package io.github.eggloop.expression;

import io.github.eggloop.expression.arithmetic.*;
import io.github.eggloop.expression.relational.DomainFunction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinusTest {

    @Test
    void testEvaluate() {
        Assignment assignment = new Assignment();
        assignment.put("X", 1.);

        ArithmeticExpression minus = new Minus(new Variable("X"));
        DomainFunction<Double> compiledFormula = minus.compile();

        assertEquals(-1., compiledFormula.evaluate(assignment));
    }
}