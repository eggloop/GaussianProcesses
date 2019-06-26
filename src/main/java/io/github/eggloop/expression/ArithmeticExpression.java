package io.github.eggloop.expression;

public interface ArithmeticExpression {

    double evaluate(Assignment assignment) throws VariableException;

}