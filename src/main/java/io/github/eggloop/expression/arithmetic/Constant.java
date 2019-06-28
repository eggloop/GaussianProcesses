package io.github.eggloop.expression.arithmetic;

public class Constant implements ArithmeticExpression {

    private double value;

    public Constant(double value) {
        this.value = value;
    }

    @Override
    public AssignmentFunction compile() {
        return assignment -> value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
