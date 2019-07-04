package io.github.eggloop.stl.syntax;

import io.github.eggloop.expression.arithmetic.Parameter;
import io.github.eggloop.expression.relational.DomainFunction;
import io.github.eggloop.stl.visitor.FormulaVisitor;

public class Interval implements Formula {

    private Parameter left;
    private Parameter right;

    public Interval(Parameter left, Parameter right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public <T> DomainFunction<T> accept(FormulaVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public Parameter getLeft() {
        return left;
    }

    public Parameter getRight() {
        return right;
    }
}
