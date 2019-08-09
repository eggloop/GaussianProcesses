package io.github.eggloop.expression.arithmetic;

import io.github.eggloop.expression.relational.DomainFunction;

import java.util.function.Predicate;

public class Variable implements ArithmeticExpression {

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


    public DomainFunction<String> print() {
        return assignment -> {
            if (assignment.contains(name)) {
                return String.valueOf(assignment.get(name));
                //return name + "[" + assignment.get(name) + "]";
            }
            return name;
        };
    }

    @Override
    public DomainFunction<Predicate<Assignment>> logicalImplication() {
        return assignment -> inner -> !assignment.contains(name) || inner.get(name) > assignment.get(name);
    }
}