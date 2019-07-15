package io.github.eggloop.expression.arithmetic;

import io.github.eggloop.expression.relational.DomainFunction;

public class Subtraction implements ArithmeticExpression {

    private ArithmeticExpression left;
    private ArithmeticExpression right;

    public Subtraction(ArithmeticExpression left, ArithmeticExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public DomainFunction<Double> compile() {
        return assignment -> left.compile().evaluate(assignment) - right.compile().evaluate(assignment);
    }

    @Override
    public DomainFunction<String> print() {
        return assignment -> left.compile().evaluate(assignment) + " - " + right.compile().evaluate(assignment);
    }
}
