package io.github.eggloop.expression.arithmetic;

public class Subtraction implements ArithmeticExpression {

    private ArithmeticExpression left;
    private ArithmeticExpression right;

    public Subtraction(ArithmeticExpression left, ArithmeticExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public AssignmentFunction compile() {
        return assignment -> left.compile().evaluate(assignment) - right.compile().evaluate(assignment);
    }
}
