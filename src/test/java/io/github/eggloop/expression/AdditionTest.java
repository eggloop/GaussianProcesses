package io.github.eggloop.expression;

import io.github.eggloop.expression.arithmetic.*;
import io.github.eggloop.expression.relational.DomainFunction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdditionTest {

    @Test
    void testEvaluate() {
        Assignment assignment = new Assignment();
        assignment.put("X", 1.);
        assignment.put("Y", 2.);

        ArithmeticExpression addition = new Addition(new Variable("X"), new Variable("Y"));
        DomainFunction<Double> compiledAddition = addition.compile();

        assertEquals(3., compiledAddition.evaluate(assignment));
    }
}