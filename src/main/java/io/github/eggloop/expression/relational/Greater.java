package io.github.eggloop.expression.relational;

import io.github.eggloop.expression.arithmetic.ArithmeticExpression;

public class Greater implements RelationalExpression {

    private ArithmeticExpression left;
    private ArithmeticExpression right;

    public Greater(ArithmeticExpression left, ArithmeticExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Object evaluate(Domain domain) {
        return domain.greaterThan(left, right);
    }

    @Override
    public String toString() {
        return left.toString() + " > " + right.toString();
    }
}
