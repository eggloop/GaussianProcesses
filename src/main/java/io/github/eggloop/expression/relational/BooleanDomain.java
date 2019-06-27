package io.github.eggloop.expression.relational;

import io.github.eggloop.expression.arithmetic.ArithmeticExpression;

public class BooleanDomain implements Domain<Boolean> {


    @Override
    public DomainFunction<Boolean> lowerEqual(ArithmeticExpression left, ArithmeticExpression right) {
        return assignment -> left.evaluate(assignment) <= right.evaluate(assignment);
    }

    @Override
    public DomainFunction<Boolean> lower(ArithmeticExpression left, ArithmeticExpression right) {
        return assignment -> left.evaluate(assignment) < right.evaluate(assignment);
    }

    @Override
    public DomainFunction<Boolean> greaterEqual(ArithmeticExpression left, ArithmeticExpression right) {
        return assignment -> left.evaluate(assignment) >= right.evaluate(assignment);
    }

    @Override
    public DomainFunction<Boolean> greater(ArithmeticExpression left, ArithmeticExpression right) {
        return assignment -> left.evaluate(assignment) > right.evaluate(assignment);
    }

    @Override
    public DomainFunction<Boolean> equal(ArithmeticExpression left, ArithmeticExpression right) {
        return assignment -> left.evaluate(assignment) == right.evaluate(assignment);
    }
}
