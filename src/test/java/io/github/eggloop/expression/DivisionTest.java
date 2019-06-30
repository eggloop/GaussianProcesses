package io.github.eggloop.expression;

import io.github.eggloop.expression.arithmetic.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DivisionTest {

    @Test
    void testEvaluate() {
        Assignment assignment = new Assignment();
        assignment.put("X", 1.);
        assignment.put("Y", 2.);

        ArithmeticExpression addition = new Division(new Variable("X"), new Variable("Y"));
        AssignmentFunction compiledAddition = addition.compile();

        assertEquals(0.5, compiledAddition.evaluate(assignment));
    }
}