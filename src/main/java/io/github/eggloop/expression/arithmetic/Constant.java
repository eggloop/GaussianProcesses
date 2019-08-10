package io.github.eggloop.expression.arithmetic;

import io.github.eggloop.expression.relational.DomainFunction;

import java.util.function.Predicate;

public class Constant implements ArithmeticExpression {

    private double value;

    public Constant(double value) {
        this.value = value;
    }

    @Override
    public <T> DomainFunction<T> accept(ArithmeticExpressionVisitor<T> visitor) {
        return visitor.visit(this);
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
    public DomainFunction<Predicate<Assignment>> logicalImplication() {
        return assignment -> inner -> true;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}