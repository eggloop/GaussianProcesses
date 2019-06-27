package io.github.eggloop.expression;

import io.github.eggloop.expression.arithmetic.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DivisionTest {

    @Test
    void testEvaluate() throws VariableException {
        Map<String, Double> variables = new HashMap<>();
        variables.put("X", 1.);
        variables.put("Y", 2.);
        Assignment assignment = new Assignment(variables);

        ArithmeticExpression addition = new Division(new Variable("X"), new Variable("Y"));

        assertEquals(0.5, addition.evaluate(assignment));
    }
}