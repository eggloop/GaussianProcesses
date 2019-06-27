package io.github.eggloop.expression.arithmetic;

public interface ArithmeticExpression {

    double evaluate(Assignment assignment) throws VariableException;

}