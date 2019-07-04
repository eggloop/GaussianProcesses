package io.github.eggloop.expression.relational;

import io.github.eggloop.expression.arithmetic.ArithmeticExpression;

public class BooleanDomain implements Domain<Boolean> {

    @Override
    public DomainFunction<Boolean> lowerEqualTo(ArithmeticExpression left, ArithmeticExpression right) {
        return assignment -> left.compile().evaluate(assignment) <= right.compile().evaluate(assignment);
    }

    @Override
    public DomainFunction<Boolean> lowerThan(ArithmeticExpression left, ArithmeticExpression right) {
        return assignment -> left.compile().evaluate(assignment) < right.compile().evaluate(assignment);
    }

    @Override
    public DomainFunction<Boolean> greaterEqualTo(ArithmeticExpression left, ArithmeticExpression right) {
        return assignment -> left.compile().evaluate(assignment) >= right.compile().evaluate(assignment);
    }

    @Override
    public DomainFunction<Boolean> greaterThan(ArithmeticExpression left, ArithmeticExpression right) {
        return assignment -> left.compile().evaluate(assignment) > right.compile().evaluate(assignment);
    }

    @Override
    public DomainFunction<Boolean> equalTo(ArithmeticExpression left, ArithmeticExpression right) {
        return assignment -> left.compile().evaluate(assignment).equals(right.compile().evaluate(assignment));
    }
}
