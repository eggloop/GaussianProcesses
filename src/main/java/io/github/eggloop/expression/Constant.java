package io.github.eggloop.expression;

public class Constant implements ArithmeticExpression {

    private double value;

    Constant(double value) {
        this.value = value;
    }

    double getValue() {
        return value;
    }

    @Override
    public ArithmeticExpression evaluate(Assignment assignment) {
        return null;
    }
}
