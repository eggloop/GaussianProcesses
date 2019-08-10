package io.github.eggloop.expression.arithmetic;

import io.github.eggloop.expression.relational.DomainFunction;
import io.github.eggloop.stl.syntax.SyntaxUtils;

import java.util.function.Predicate;

public class Minus implements ArithmeticExpression {

    private ArithmeticExpression argument;

    public Minus(ArithmeticExpression argument) {
        this.argument = argument;
    }

    @Override
    public <T> DomainFunction<T> accept(ArithmeticExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public DomainFunction<Double> compile() {
        return assignment -> -argument.compile().evaluate(assignment);
    }

    @Override
    public DomainFunction<String> print() {
        return assignment -> SyntaxUtils.toStringUnaryFormula("-", argument.print().evaluate(assignment));
    }
    @Override
    public DomainFunction<Predicate<Assignment>> logicalImplication() {
        DomainFunction<Predicate<Assignment>> predicate = argument.logicalImplication();
        return assignment -> predicate.evaluate(assignment).negate();
    }

    @Override
    public String toString() {
        return SyntaxUtils.toStringUnaryFormula("-", argument.toString());
    }
}