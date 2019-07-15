package io.github.eggloop.expression.arithmetic;

import io.github.eggloop.expression.relational.DomainFunction;

public class Multiplication implements ArithmeticExpression {

    private ArithmeticExpression left;
    private ArithmeticExpression right;

    public Multiplication(ArithmeticExpression left, ArithmeticExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public DomainFunction<Double> compile() {
        return assignment -> left.compile().evaluate(assignment) * right.compile().evaluate(assignment);
    }

    @Override
    public DomainFunction<String> print() {
        return assignment -> left.print().evaluate(assignment) + " * " + right.compile().evaluate(assignment);
    }
}
