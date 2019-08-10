package io.github.eggloop.expression.arithmetic;

import io.github.eggloop.expression.relational.DomainFunction;
import io.github.eggloop.stl.syntax.SyntaxUtils;

import java.util.function.Predicate;

public class Addition implements ArithmeticExpression {

    private ArithmeticExpression left;
    private ArithmeticExpression right;

    public Addition(ArithmeticExpression left, ArithmeticExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public <T> DomainFunction<T> accept(ArithmeticExpressionVisitor<T> visitor) {
        return visitor.visit(this);
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
    public DomainFunction<Predicate<Assignment>> logicalImplication() {
        DomainFunction<Predicate<Assignment>> predicateLeft = left.logicalImplication();
        DomainFunction<Predicate<Assignment>> predicateRight = right.logicalImplication();
        return assignment -> predicateLeft.evaluate(assignment).and(predicateRight.evaluate(assignment));
    }


    @Override
    public String toString() {
        return SyntaxUtils.toStringBinaryFormula(left.toString(), "+", right.toString());
    }
}
