package io.github.eggloop.expression.arithmetic;

import io.github.eggloop.expression.relational.DomainFunction;
import io.github.eggloop.stl.syntax.SyntaxUtils;

public class Minus implements ArithmeticExpression {

    private ArithmeticExpression argument;

    public Minus(ArithmeticExpression argument) {
        this.argument = argument;
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
    public String toString() {
        return SyntaxUtils.toStringUnaryFormula("-", argument.toString());
    }
}