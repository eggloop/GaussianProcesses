package io.github.eggloop.expression.relational;

import io.github.eggloop.expression.arithmetic.ArithmeticExpression;

public class Lower implements RelationalExpression {

    private ArithmeticExpression left;
    private ArithmeticExpression right;

    public Lower(ArithmeticExpression left, ArithmeticExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Object evaluate(Domain domain) {
        return domain.lowerThan(left,right);    }
}
