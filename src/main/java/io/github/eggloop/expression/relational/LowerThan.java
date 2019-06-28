package io.github.eggloop.expression.relational;

import io.github.eggloop.expression.arithmetic.ArithmeticExpression;

public class LowerThan implements RelationalExpression {

    private ArithmeticExpression left;
    private ArithmeticExpression right;

    public LowerThan(ArithmeticExpression left, ArithmeticExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public DomainFunction evaluate(Domain domain) {
        return domain.lowerThan(left,right);    }
}
