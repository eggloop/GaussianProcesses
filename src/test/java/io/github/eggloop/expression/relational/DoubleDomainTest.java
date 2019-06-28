package io.github.eggloop.expression.relational;

import io.github.eggloop.expression.arithmetic.ArithmeticExpression;
import io.github.eggloop.expression.arithmetic.Assignment;
import io.github.eggloop.expression.arithmetic.Variable;
import io.github.eggloop.expression.arithmetic.VariableException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class DoubleDomainTest {
    private static final double EPS = 1E-14;
    private DomainFunction<Double> lowerEqualThan;
    private DomainFunction<Double> lowerThan;
    private DomainFunction<Double> greaterThan;
    private DomainFunction<Double> greaterEqualTo;
    private DomainFunction<Double> equalTo;

    @BeforeEach
    void setUp() {
        DoubleDomain domain = new DoubleDomain();
        ArithmeticExpression left = new Variable("X");
        ArithmeticExpression right = new Variable("Y");
        lowerEqualThan = domain.lowerEqualThan(left, right);
        lowerThan = domain.lowerThan(left, right);
        greaterThan = domain.greaterThan(left, right);
        greaterEqualTo = domain.greaterEqualTo(left, right);
        equalTo = domain.equalTo(left, right);
    }

    @Test
    void test1() throws VariableException {
        Map<String, Double> assignmentMap = new HashMap<>();
        assignmentMap.put("X", 10.);
        assignmentMap.put("Y", 2.);
        Assignment assignment = new Assignment(assignmentMap);

        Assertions.assertEquals(-8., lowerThan.evaluate(assignment));
        Assertions.assertEquals(-8., lowerEqualThan.evaluate(assignment));
        Assertions.assertEquals(8., greaterThan.evaluate(assignment));
        Assertions.assertEquals(8., greaterEqualTo.evaluate(assignment));
        Assertions.assertEquals(-8., equalTo.evaluate(assignment));
    }

    @Test
    void test2() throws VariableException {
        Map<String, Double> assignmentMap = new HashMap<>();
        assignmentMap.put("X", 2.);
        assignmentMap.put("Y", 10.);
        Assignment assignment = new Assignment(assignmentMap);

        Assertions.assertEquals(8., lowerThan.evaluate(assignment));
        Assertions.assertEquals(8., lowerEqualThan.evaluate(assignment));
        Assertions.assertEquals(-8., greaterThan.evaluate(assignment));
        Assertions.assertEquals(-8., greaterEqualTo.evaluate(assignment));
        Assertions.assertEquals(-8., equalTo.evaluate(assignment));
    }

    @Test
    void test3() throws VariableException {
        Map<String, Double> assignmentMap = new HashMap<>();
        assignmentMap.put("X", 2.);
        assignmentMap.put("Y", 2.);
        Assignment assignment = new Assignment(assignmentMap);

        Assertions.assertEquals(0, lowerThan.evaluate(assignment));
        Assertions.assertEquals(0, lowerEqualThan.evaluate(assignment));
        Assertions.assertEquals(0, greaterThan.evaluate(assignment));
        Assertions.assertEquals(0, greaterEqualTo.evaluate(assignment));
        Assertions.assertEquals(0, equalTo.evaluate(assignment));
    }

}