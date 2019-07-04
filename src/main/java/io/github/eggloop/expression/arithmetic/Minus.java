package io.github.eggloop.expression.arithmetic;

import io.github.eggloop.expression.relational.DomainFunction;

public class Minus implements ArithmeticExpression {

    private ArithmeticExpression argument;

    public Minus(ArithmeticExpression argument) {
        this.argument = argument;
    }

    @Override
    public DomainFunction<Double> compile() {
        return assignment -> -argument.compile().evaluate(assignment);
    }
}
