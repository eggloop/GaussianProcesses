package io.github.eggloop.expression.arithmetic;

public class Multiplication implements ArithmeticExpression {

    private ArithmeticExpression left;
    private ArithmeticExpression right;

    public Multiplication(ArithmeticExpression left, ArithmeticExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public double evaluate(Assignment assignment) throws VariableException {
        return left.compile(assignment) * right.compile(assignment);
    }
}
