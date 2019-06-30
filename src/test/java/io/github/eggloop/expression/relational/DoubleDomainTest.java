package io.github.eggloop.expression.relational;

import io.github.eggloop.expression.arithmetic.ArithmeticExpression;
import io.github.eggloop.expression.arithmetic.Assignment;
import io.github.eggloop.expression.arithmetic.Variable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DoubleDomainTest {
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
        lowerEqualThan = domain.lowerEqualTo(left, right);
        lowerThan = domain.lowerThan(left, right);
        greaterThan = domain.greaterThan(left, right);
        greaterEqualTo = domain.greaterEqualTo(left, right);
        equalTo = domain.equalTo(left, right);
    }

    @Test
    void test1() {
        Assignment assignment = new Assignment();
        assignment.put("X", 10.);
        assignment.put("Y", 2.);

        Assertions.assertEquals(-8., lowerThan.evaluate(assignment));
        Assertions.assertEquals(-8., lowerEqualThan.evaluate(assignment));
        Assertions.assertEquals(8., greaterThan.evaluate(assignment));
        Assertions.assertEquals(8., greaterEqualTo.evaluate(assignment));
        Assertions.assertEquals(-8., equalTo.evaluate(assignment));
    }

    @Test
    void test2() {
        Assignment assignment = new Assignment();
        assignment.put("X", 2.);
        assignment.put("Y", 10.);

        Assertions.assertEquals(8., lowerThan.evaluate(assignment));
        Assertions.assertEquals(8., lowerEqualThan.evaluate(assignment));
        Assertions.assertEquals(-8., greaterThan.evaluate(assignment));
        Assertions.assertEquals(-8., greaterEqualTo.evaluate(assignment));
        Assertions.assertEquals(-8., equalTo.evaluate(assignment));
    }

    @Test
    void test3() {
        Assignment assignment = new Assignment();
        assignment.put("X", 2.);
        assignment.put("Y", 2.);

        Assertions.assertEquals(0, lowerThan.evaluate(assignment));
        Assertions.assertEquals(0, lowerEqualThan.evaluate(assignment));
        Assertions.assertEquals(0, greaterThan.evaluate(assignment));
        Assertions.assertEquals(0, greaterEqualTo.evaluate(assignment));
        Assertions.assertEquals(0, equalTo.evaluate(assignment));
    }

}