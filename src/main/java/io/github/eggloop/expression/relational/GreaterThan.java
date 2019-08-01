package io.github.eggloop.expression.relational;

import io.github.eggloop.expression.arithmetic.ArithmeticExpression;
import io.github.eggloop.stl.syntax.SyntaxUtils;

public class GreaterThan implements RelationalExpression {

    private ArithmeticExpression left;
    private ArithmeticExpression right;

    public GreaterThan(ArithmeticExpression left, ArithmeticExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public DomainFunction compile(Domain domain) {
        return domain.greaterThan(left, right);
    }

    @Override
    public DomainFunction<String> print() {
        return assignment -> SyntaxUtils.toStringBinaryFormula(left.print().evaluate(assignment), ">", right.print().evaluate(assignment));
    }

    @Override
    public String toString() {
        return SyntaxUtils.toStringBinaryFormula(left.toString(), ">", right.toString());
    }
}
