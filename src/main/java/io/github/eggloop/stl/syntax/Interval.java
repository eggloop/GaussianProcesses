package io.github.eggloop.stl.syntax;

import io.github.eggloop.expression.arithmetic.ArithmeticExpression;

public class Interval {

    private ArithmeticExpression left;
    private ArithmeticExpression right;

    public Interval(ArithmeticExpression left, ArithmeticExpression right) {
        this.left = left;
        this.right = right;
    }

    public ArithmeticExpression getLeft() {
        return left;
    }

    public ArithmeticExpression getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "[" + left + " , " + right + "]";
    }
}
