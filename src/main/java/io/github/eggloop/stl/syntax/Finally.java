package io.github.eggloop.stl.syntax;

import io.github.eggloop.expression.relational.DomainFunction;
import io.github.eggloop.stl.visitor.FormulaVisitor;

public class Finally implements Formula {

    private Interval interval;
    private Formula argument;

    public Finally(Interval interval, Formula argument) {
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
        return "F_" + interval.toString() + argument.toString();
    }
}
