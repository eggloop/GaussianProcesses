package io.github.eggloop.expression.relational;

import io.github.eggloop.expression.arithmetic.ArithmeticExpression;

public class EqualTo implements RelationalExpression {

    private ArithmeticExpression left;
    private ArithmeticExpression right;

    public EqualTo(ArithmeticExpression left, ArithmeticExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public DomainFunction evaluate(Domain domain) {
        return domain.equalTo(left, right);
    }

    @Override
    public String toString() {
        return left.toString() + " == " + right.toString();
    }
}
