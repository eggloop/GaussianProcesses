package io.github.eggloop.expression.relational;

import io.github.eggloop.expression.arithmetic.ArithmeticExpression;

public class GreaterEqualTo implements RelationalExpression {

    private ArithmeticExpression left;
    private ArithmeticExpression right;

    public GreaterEqualTo(ArithmeticExpression left, ArithmeticExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public DomainFunction compile(Domain domain) {
        return domain.greaterEqualTo(left,right);
    }

    @Override
    public String toString() {
        return left.toString() + " >= " + right.toString();
    }
}
