package io.github.eggloop.expression;

import io.github.eggloop.expression.arithmetic.ArithmeticExpression;
import io.github.eggloop.expression.arithmetic.Assignment;
import io.github.eggloop.expression.arithmetic.Variable;
import io.github.eggloop.expression.arithmetic.VariableException;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class VariableTest {

    @Test
    void tesEvaluateWhenThereIsAssignment() throws VariableException {
        Map<String, Double> variables = new HashMap<>();
        variables.put("X", 1.);
        Assignment assignment = new Assignment(variables);

        ArithmeticExpression addition = new Variable("X");

        assertEquals(1., addition.evaluate(assignment));
    }

    @Test
    void tesEvaluateWhenThereIsNoAssignment() {
        Map<String, Double> variables = new HashMap<>();
        variables.put("Y", 1.);
        Assignment assignment = new Assignment(variables);

        ArithmeticExpression addition = new Variable("X");

        VariableException variableException = assertThrows(VariableException.class, () -> addition.evaluate(assignment));

        assertEquals("MISSING VARIABLES X",variableException.getMessage());

    }
}