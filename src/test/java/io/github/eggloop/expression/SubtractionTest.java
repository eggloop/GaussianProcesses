package io.github.eggloop.expression;

import io.github.eggloop.expression.arithmetic.ArithmeticExpression;
import io.github.eggloop.expression.arithmetic.Assignment;
import io.github.eggloop.expression.arithmetic.Subtraction;
import io.github.eggloop.expression.arithmetic.Variable;
import io.github.eggloop.expression.relational.DomainFunction;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class SubtractionTest {

    @Test
    void testEvaluate() {
        Assignment assignment = new Assignment();
        assignment.put("X", 1.);
        assignment.put("Y", 2.);

        ArithmeticExpression addition = new Subtraction(new Variable("X"), new Variable("Y"));
        DomainFunction<Double> compiledAddition = addition.compile();

        assertEquals(-1., compiledAddition.evaluate(assignment));
    }

    @Test
    void testPrint() {
        Assignment assignment = new Assignment();
        assignment.put("X", 1.);

        ArithmeticExpression subtraction = new Subtraction(new Variable("X"), new Variable("Y"));
        DomainFunction<String> printSubtraction = subtraction.print();

        assertEquals("(1.0 - Y)", printSubtraction.evaluate(assignment));
    }

    @Test
    void testToString() {
        ArithmeticExpression subtraction = new Subtraction(new Variable("X"), new Variable("Y"));

        assertEquals("(X - Y)", subtraction.toString());
    }

    @Test
    void testLogicalImplicationAllVariableAssigned() {
        ArithmeticExpression left = new Variable("X");
        ArithmeticExpression right = new Variable("Y");
        ArithmeticExpression subtraction = new Subtraction(left,right);
        Assignment originalAssignment = new Assignment();
        originalAssignment.put("X", 2);
        originalAssignment.put("Y", 5);
        Assignment trueAssignment = new Assignment();
        trueAssignment.put("X", 3);
        trueAssignment.put("Y", 4);
        Assignment falseAssignment = new Assignment();
        falseAssignment.put("X",1);
        falseAssignment.put("Y", 10);

        Predicate<Assignment> predicate = subtraction.logicalImplication().evaluate(originalAssignment);

        assertFalse(predicate.test(originalAssignment));
        assertTrue(predicate.test(trueAssignment));
        assertFalse(predicate.test(falseAssignment));
    }

    @Test
    void testLogicalImplicationPartialVariableAssignment() {
        ArithmeticExpression left = new Variable("X");
        ArithmeticExpression right = new Variable("Y");
        ArithmeticExpression subtraction = new Subtraction(left,right);
        Assignment originalAssignment = new Assignment();
        originalAssignment.put("X", 2);
        originalAssignment.put("Y", 5);
        Assignment trueAssignment = new Assignment();
        trueAssignment.put("X", 3);
        trueAssignment.put("Y", 4);
        Assignment falseAssignment = new Assignment();
        falseAssignment.put("X",1);
        falseAssignment.put("Y", 10);

        Predicate<Assignment> predicate = subtraction.logicalImplication().evaluate(originalAssignment);

        assertFalse(predicate.test(originalAssignment));
        assertTrue(predicate.test(trueAssignment));
        assertFalse(predicate.test(falseAssignment));
    }
}