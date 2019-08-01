package io.github.eggloop.expression;

import io.github.eggloop.expression.arithmetic.*;
import io.github.eggloop.expression.relational.DomainFunction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DivisionTest {

    @Test
    void testEvaluate() {
        Assignment assignment = new Assignment();
        assignment.put("X", 1.);
        assignment.put("Y", 2.);

        ArithmeticExpression division = new Division(new Variable("X"), new Variable("Y"));
        DomainFunction<Double> compiledAddition = division.compile();

        assertEquals(0.5, compiledAddition.evaluate(assignment));
    }

    @Test
    void testPrint() {
        Assignment assignment = new Assignment();
        assignment.put("X", 1.);

        ArithmeticExpression division = new Division(new Variable("X"), new Variable("Y"));
        DomainFunction<String> printDivision = division.print();

        assertEquals("(1.0 / Y)", printDivision.evaluate(assignment));
    }

    @Test
    void testToString() {
        ArithmeticExpression division = new Division(new Variable("X"), new Variable("Y"));

        assertEquals("(X / Y)", division.toString());
    }
}