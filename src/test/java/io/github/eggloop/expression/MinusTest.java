package io.github.eggloop.expression;

import io.github.eggloop.expression.arithmetic.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinusTest {

    @Test
    void testEvaluate() {
        Assignment assignment = new Assignment();
        assignment.put("X", 1.);

        ArithmeticExpression addition = new Minus(new Variable("X"));
        AssignmentFunction compiledFormula = addition.compile();

        assertEquals(-1., compiledFormula.evaluate(assignment));
    }
}