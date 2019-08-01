package io.github.eggloop.expression;

import io.github.eggloop.expression.arithmetic.*;
import io.github.eggloop.expression.relational.DomainFunction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MultiplicationTest {

    @Test
    void testEvaluate() {
        Assignment assignment = new Assignment();
        assignment.put("X", 1.);
        assignment.put("Y", 2.);

        ArithmeticExpression addition = new Multiplication(new Variable("X"), new Variable("Y"));
        DomainFunction<Double> compiledAddition = addition.compile();

        assertEquals(2., compiledAddition.evaluate(assignment));
    }

    @Test
    void testPrint() {
        Assignment assignment = new Assignment();
        assignment.put("X", 1.);

        ArithmeticExpression multiplication = new Multiplication(new Variable("X"), new Variable("Y"));
        DomainFunction<String> printMultiplication = multiplication.print();

        assertEquals("(1.0 * Y)", printMultiplication.evaluate(assignment));
    }

    @Test
    void testToString() {
        ArithmeticExpression multiplication = new Multiplication(new Variable("X"), new Variable("Y"));

        assertEquals("(X * Y)", multiplication.toString());
    }
}