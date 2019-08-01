package io.github.eggloop.expression.arithmetic;

import io.github.eggloop.expression.relational.DomainFunction;
import io.github.eggloop.stl.syntax.SyntaxUtils;

public class Addition implements ArithmeticExpression {

    private ArithmeticExpression left;
    private ArithmeticExpression right;

    public Addition(ArithmeticExpression left, ArithmeticExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public DomainFunction<Double> compile() {
        return assignment -> left.compile().evaluate(assignment) + right.compile().evaluate(assignment);
    }

    @Override
    public DomainFunction<String> print() {
        return assignment -> SyntaxUtils.toStringBinaryFormula(left.print().evaluate(assignment), "+", right.print().evaluate(assignment));
    }

    @Override
    public String toString() {
        return SyntaxUtils.toStringBinaryFormula(left.toString(), "+", right.toString());
    }
}
