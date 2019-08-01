package io.github.eggloop.expression;

import io.github.eggloop.expression.arithmetic.ArithmeticExpression;
import io.github.eggloop.expression.arithmetic.Assignment;
import io.github.eggloop.expression.arithmetic.Subtraction;
import io.github.eggloop.expression.arithmetic.Variable;
import io.github.eggloop.expression.relational.DomainFunction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}