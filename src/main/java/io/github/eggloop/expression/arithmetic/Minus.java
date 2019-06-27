package io.github.eggloop.expression.arithmetic;

public class Minus implements ArithmeticExpression {

    private ArithmeticExpression argument;

    public Minus(ArithmeticExpression argument) {
        this.argument = argument;
    }

    @Override
    public double evaluate(Assignment assignment) throws VariableException {
        return -argument.evaluate(assignment);
    }
}
