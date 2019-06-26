package io.github.eggloop.expression;

public class Subtraction implements ArithmeticExpression {

    private ArithmeticExpression left;
    private ArithmeticExpression right;

    public Subtraction(ArithmeticExpression left, ArithmeticExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public double evaluate(Assignment assignment) throws VariableException {
        return left.evaluate(assignment) - right.evaluate(assignment);
    }
}
