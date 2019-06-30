package io.github.eggloop.expression.relational;

import io.github.eggloop.expression.arithmetic.ArithmeticExpression;
import io.github.eggloop.expression.arithmetic.Assignment;
import io.github.eggloop.expression.arithmetic.Variable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BooleanDomainTest {

    private DomainFunction<Boolean> lowerEqualThan;
    private DomainFunction<Boolean> lowerThan;
    private DomainFunction<Boolean> greaterThan;
    private DomainFunction<Boolean> greaterEqualTo;
    private DomainFunction<Boolean> equalTo;

    @BeforeEach
    void setUp() {
        BooleanDomain domain = new BooleanDomain();
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
        assignment.put("X", 10);
        assignment.put("Y", 2.);

        Assertions.assertFalse(lowerThan.evaluate(assignment));
        Assertions.assertFalse(lowerEqualThan.evaluate(assignment));
        Assertions.assertTrue(greaterThan.evaluate(assignment));
        Assertions.assertTrue(greaterEqualTo.evaluate(assignment));
        Assertions.assertFalse(equalTo.evaluate(assignment));
    }

    @Test
    void test2() {
        Assignment assignment = new Assignment();
        assignment.put("X", 2.);
        assignment.put("Y", 10.);

        Assertions.assertTrue(lowerThan.evaluate(assignment));
        Assertions.assertTrue(lowerEqualThan.evaluate(assignment));
        Assertions.assertFalse(greaterThan.evaluate(assignment));
        Assertions.assertFalse(greaterEqualTo.evaluate(assignment));
        Assertions.assertFalse(equalTo.evaluate(assignment));
    }

    @Test
    void test3() {
        Assignment assignment = new Assignment();
        assignment.put("X", 2.);
        assignment.put("Y", 2.);

        Assertions.assertFalse(lowerThan.evaluate(assignment));
        Assertions.assertTrue(lowerEqualThan.evaluate(assignment));
        Assertions.assertFalse(greaterThan.evaluate(assignment));
        Assertions.assertTrue(greaterEqualTo.evaluate(assignment));
        Assertions.assertTrue(equalTo.evaluate(assignment));
    }
}