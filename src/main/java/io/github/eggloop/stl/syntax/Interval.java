package io.github.eggloop.stl.syntax;

import io.github.eggloop.expression.arithmetic.Parameter;

public class Interval {

    private Parameter left;
    private Parameter right;

    public Interval(Parameter left, Parameter right) {
        this.left = left;
        this.right = right;
    }

    public Parameter getLeft() {
        return left;
    }

    public Parameter getRight() {
        return right;
    }
}
