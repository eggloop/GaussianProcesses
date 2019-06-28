package io.github.eggloop.expression.relational;

import io.github.eggloop.expression.arithmetic.ArithmeticExpression;

public class Equal implements RelationalExpression {

    private ArithmeticExpression left;
    private ArithmeticExpression right;

    public Equal(ArithmeticExpression left, ArithmeticExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Object evaluate(Domain domain) {
        return domain.equalTo(left, right);
    }
}
