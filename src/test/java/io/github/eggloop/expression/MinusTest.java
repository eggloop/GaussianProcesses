package io.github.eggloop.expression;

import io.github.eggloop.expression.arithmetic.ArithmeticExpression;
import io.github.eggloop.expression.arithmetic.Assignment;
import io.github.eggloop.expression.arithmetic.Minus;
import io.github.eggloop.expression.arithmetic.Variable;
import io.github.eggloop.expression.relational.DomainFunction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinusTest {

    @Test
    void testEvaluate() {
        Assignment assignment = new Assignment();
        assignment.put("X", 1.);

        ArithmeticExpression minus = new Minus(new Variable("X"));
        DomainFunction<Double> compiledFormula = minus.compile();

        assertEquals(-1., compiledFormula.evaluate(assignment));
    }

    @Test
    void testPrint() {
        Assignment assignment = new Assignment();
        assignment.put("X", 1.);

        ArithmeticExpression minus = new Minus(new Variable("X"));
        DomainFunction<String> printMinus = minus.print();

        assertEquals("-(1.0)", printMinus.evaluate(assignment));
    }

    @Test
    void testToString() {
        ArithmeticExpression minus = new Minus(new Variable("X"));
        assertEquals("-(X)", minus.toString());
    }
}