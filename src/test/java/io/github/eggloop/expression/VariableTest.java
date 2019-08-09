package io.github.eggloop.expression;

import io.github.eggloop.expression.arithmetic.ArithmeticExpression;
import io.github.eggloop.expression.arithmetic.Assignment;
import io.github.eggloop.expression.arithmetic.Variable;
import io.github.eggloop.expression.relational.DomainFunction;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class VariableTest {

    @Test
    void tesEvaluateWhenThereIsAssignment() {
        Assignment assignment = new Assignment();
        assignment.put("X", 1.);

        ArithmeticExpression variable = new Variable("X");
        DomainFunction<Double> compiledAddition = variable.compile();

        assertEquals(1., compiledAddition.evaluate(assignment));
    }

    @Test
    void testPrintWhenThereIsAssignment() {
        Assignment assignment = new Assignment();
        assignment.put("X", 1.);

        ArithmeticExpression variable = new Variable("X");
        DomainFunction<String> printVariable = variable.print();

        assertEquals("1.0", printVariable.evaluate(assignment));
    }

    @Test
    void testPrintWhenThereIsNoAssignment() {
        Assignment assignment = new Assignment();

        ArithmeticExpression variable = new Variable("X");
        DomainFunction<String> printVariable = variable.print();

        assertEquals("X", printVariable.evaluate(assignment));
    }

    @Test
    void testToString() {
        ArithmeticExpression variable = new Variable("X");

        assertEquals("X", variable.toString());
    }

    @Test
    void testLogicalImplication() {
        ArithmeticExpression variable = new Variable("X");
        Assignment originalAssignment = new Assignment();
        originalAssignment.put("X", 2);
        Assignment trueAssignment = new Assignment();
        trueAssignment.put("X", 3);
        Assignment falseAssignment = new Assignment();
        falseAssignment.put("X", 1.9);

        Predicate<Assignment> predicate = variable.logicalImplication().evaluate(originalAssignment);

        assertFalse(predicate.test(originalAssignment));
        assertTrue(predicate.test(trueAssignment));
        assertFalse(predicate.test(falseAssignment));
    }

}