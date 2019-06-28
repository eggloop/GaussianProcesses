package io.github.eggloop.expression.relational;

import io.github.eggloop.expression.arithmetic.*;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class EqualToTest {



    @Test
    void evaluate() throws VariableException {
        ArithmeticExpression left = new Variable("X");
        ArithmeticExpression right = new Constant(0);
        EqualTo equal = new EqualTo(left,right);

        BooleanDomain domain = new BooleanDomain();

        DomainFunction evaluate = equal.evaluate(domain);

        Assignment<Double> assignment = new Assignment<>();
        assignment.put("X",10.);
        evaluate.evaluate(assignment);

    }
}