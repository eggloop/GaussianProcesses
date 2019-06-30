package io.github.eggloop.expression;

import io.github.eggloop.expression.arithmetic.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VariableTest {

    @Test
    void tesEvaluateWhenThereIsAssignment() {
        Assignment assignment = new Assignment();
        assignment.put("X", 1.);

        ArithmeticExpression addition = new Variable("X");
        AssignmentFunction compiledAddition = addition.compile();

        assertEquals(1., compiledAddition.evaluate(assignment));
    }

//    @Test
    void tesEvaluateWhenThereIsNoAssignment() {
        Assignment assignment = new Assignment();
        assignment.put("Y", 1.);

        ArithmeticExpression addition = new Variable("X");
        AssignmentFunction compiledAddition = addition.compile();

//        VariableException variableException = assertThrows(VariableException.class, () -> compiledAddition.compile(assignment));

        //assertEquals("MISSING VARIABLES X", variableException.getMessage());
        //TODO:add exception handling

    }
}