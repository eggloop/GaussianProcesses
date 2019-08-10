package io.github.eggloop.stl.syntax;

import io.github.eggloop.expression.relational.DomainFunction;

public class Globally implements Formula {

    private Interval interval;
    private Formula argument;

    public Globally(Interval interval, Formula argument) {
        this.interval = interval;
        this.argument = argument;
    }

    @Override
    public <T> DomainFunction<T> accept(FormulaVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public Interval getInterval() {
        return interval;
    }

    public Formula getArgument() {
        return argument;
    }

    @Override
    public String toString() {
        return "G_" + interval.toString() + argument.toString();
    }

    @Override
    public String hash() {
        return "G_" + argument.toString();
    }
}
