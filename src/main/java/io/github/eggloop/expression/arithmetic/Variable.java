package io.github.eggloop.expression.arithmetic;

import io.github.eggloop.expression.relational.DomainFunction;
import io.github.eggloop.stl.visitor.FormulaVisitor;

public class Variable implements ArithmeticExpression, Parameter {

    private String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public DomainFunction<Double> compile() {
        return assignment -> assignment.get(name);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public <T> DomainFunction<T> accept(FormulaVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public DomainFunction<String> print() {
        return assignment -> {
            if (assignment.contains(name)) {
                return name + "[" + assignment.get(name) + "]";
            }
            return name;
        };
    }
}
