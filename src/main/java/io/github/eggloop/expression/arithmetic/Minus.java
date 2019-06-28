package io.github.eggloop.expression.arithmetic;

public class Minus implements ArithmeticExpression {

    private ArithmeticExpression argument;

    public Minus(ArithmeticExpression argument) {
        this.argument = argument;
    }

    @Override
    public AssignmentFunction compile() {
        return assignment -> -argument.compile().evaluate(assignment);
    }
}
