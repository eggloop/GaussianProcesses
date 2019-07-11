package io.github.eggloop.expression.relational;

import io.github.eggloop.expression.arithmetic.ArithmeticExpression;

public class DoubleDomain implements Domain<Double> {

    @Override
    public DomainFunction<Double> lowerEqualTo(ArithmeticExpression left, ArithmeticExpression right) {
        return assignment -> right.compile().evaluate(assignment) - left.compile().evaluate(assignment);
    }

    @Override
    public DomainFunction<Double> lowerThan(ArithmeticExpression left, ArithmeticExpression right) {
        return assignment -> right.compile().evaluate(assignment) - left.compile().evaluate(assignment);
    }

    @Override
    public DomainFunction<Double> greaterEqualTo(ArithmeticExpression left, ArithmeticExpression right) {
        return assignment -> left.compile().evaluate(assignment) - right.compile().evaluate(assignment);
    }

    @Override
    public DomainFunction<Double> greaterThan(ArithmeticExpression left, ArithmeticExpression right) {
        return assignment -> left.compile().evaluate(assignment) - right.compile().evaluate(assignment);
    }

    @Override
    public DomainFunction<Double> equalTo(ArithmeticExpression left, ArithmeticExpression right) {
        return assignment -> 0. - Math.abs(left.compile().evaluate(assignment) - right.compile().evaluate(assignment));
    }

    @Override
    public Double conjunction(Double left, Double right) {
        return Math.min(left,right);
    }

    @Override
    public Double disjunction(Double left, Double right) {
        return Math.max(left,right);
    }

    @Override
    public Double negation(Double argument) {
        return -argument;
    }
}
