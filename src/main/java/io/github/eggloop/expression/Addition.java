package io.github.eggloop.expression;

public class Addition implements ArithmeticExpression {

    ArithmeticExpression left;
    ArithmeticExpression right;

    public Addition(ArithmeticExpression left, ArithmeticExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public ArithmeticExpression evaluate(Assignment assignment) {
        boolean leftIsConstant = left instanceof Constant;
        boolean rightIsConstant = right instanceof Constant;
        if (leftIsConstant && rightIsConstant) {
            return new Constant(((Constant) left).getValue() + ((Constant) right).getValue());
        }
        return new Addition(left.evaluate(assignment), right.evaluate(assignment));
    }
}
