package io.github.eggloop.expression.relational;

import io.github.eggloop.expression.arithmetic.ArithmeticExpression;

public class BooleanDomain implements Domain<Boolean> {

    @Override
    public DomainFunction<Boolean> lowerEqualTo(ArithmeticExpression left, ArithmeticExpression right) {
        return assignment -> left.compile(assignment) <= right.compile(assignment);
    }

    @Override
    public DomainFunction<Boolean> lowerThan(ArithmeticExpression left, ArithmeticExpression right) {
        return assignment -> left.compile(assignment) < right.compile(assignment);
    }

    @Override
    public DomainFunction<Boolean> greaterEqualTo(ArithmeticExpression left, ArithmeticExpression right) {
        return assignment -> left.compile(assignment) >= right.compile(assignment);
    }

    @Override
    public DomainFunction<Boolean> greaterThan(ArithmeticExpression left, ArithmeticExpression right) {
        return assignment -> left.compile(assignment) > right.compile(assignment);
    }

    @Override
    public DomainFunction<Boolean> equalTo(ArithmeticExpression left, ArithmeticExpression right) {
        return assignment -> left.compile(assignment) == right.compile(assignment);
    }
}
