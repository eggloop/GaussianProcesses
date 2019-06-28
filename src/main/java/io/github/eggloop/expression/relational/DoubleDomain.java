package io.github.eggloop.expression.relational;

import io.github.eggloop.expression.arithmetic.ArithmeticExpression;

public class DoubleDomain implements Domain<Double> {

    @Override
    public DomainFunction<Double> lowerEqualTo(ArithmeticExpression left, ArithmeticExpression right) {
        return assignment -> right.compile(assignment) - left.compile(assignment);
    }

    @Override
    public DomainFunction<Double> lowerThan(ArithmeticExpression left, ArithmeticExpression right) {
        return assignment -> right.compile(assignment) - left.compile(assignment);
    }

    @Override
    public DomainFunction<Double> greaterEqualTo(ArithmeticExpression left, ArithmeticExpression right) {
        return assignment -> left.compile(assignment) - right.compile(assignment);
    }

    @Override
    public DomainFunction<Double> greaterThan(ArithmeticExpression left, ArithmeticExpression right) {
        return assignment -> left.compile(assignment) - right.compile(assignment);
    }

    @Override
    public DomainFunction<Double> equalTo(ArithmeticExpression left, ArithmeticExpression right) {
        return assignment -> 0. - Math.abs(left.compile(assignment) - right.compile(assignment));
    }
}
