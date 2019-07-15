package io.github.eggloop.expression.arithmetic;

import io.github.eggloop.expression.relational.DomainFunction;

public class Constant implements ArithmeticExpression {

    private double value;

    public Constant(double value) {
        this.value = value;
    }

    @Override
    public DomainFunction<Double> compile() {
        return assignment -> value;
    }

    @Override
    public DomainFunction<String> print() {
        return assignment -> String.valueOf(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
