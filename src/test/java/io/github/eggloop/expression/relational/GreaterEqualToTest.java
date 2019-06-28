package io.github.eggloop.expression.relational;

import io.github.eggloop.expression.arithmetic.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GreaterEqualToTest {

    @Test
    void evaluateTest() throws VariableException {
        ArithmeticExpression left = new Variable("X");
        ArithmeticExpression right = new Constant(0);
        Assignment<Double> assignment = new Assignment<>();
        assignment.put("X", 1.);
        RelationalExpression equal = new GreaterEqualTo(left, right);

        BooleanDomain domain = new BooleanDomain();
        DomainFunction<Boolean> compiledEqualTo = equal.evaluate(domain);

        Assertions.assertTrue(compiledEqualTo.evaluate(assignment));
    }

}