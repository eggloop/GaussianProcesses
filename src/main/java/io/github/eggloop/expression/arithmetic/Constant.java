package io.github.eggloop.expression.arithmetic;

import io.github.eggloop.expression.relational.DomainFunction;
import io.github.eggloop.stl.visitor.FormulaVisitor;

public class Constant implements ArithmeticExpression, Parameter {

    private double value;

    public Constant(double value) {
        this.value = value;
    }

    @Override
    public DomainFunction<Double> compile() {
        return assignment -> value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public <T> DomainFunction<T> accept(FormulaVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public DomainFunction<String> print() {
        return assignment -> String.valueOf(value);
    }
}
