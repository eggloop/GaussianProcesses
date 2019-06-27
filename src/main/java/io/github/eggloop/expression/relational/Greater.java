package io.github.eggloop.expression.relational;

import io.github.eggloop.expression.arithmetic.ArithmeticExpression;

public class Greater implements RelationalExpression {

    public ArithmeticExpression left;
    public ArithmeticExpression right;

    public Greater(ArithmeticExpression left, ArithmeticExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Object evaluate(Domain domain) {
        return domain.greater(left,right);
    }
}
