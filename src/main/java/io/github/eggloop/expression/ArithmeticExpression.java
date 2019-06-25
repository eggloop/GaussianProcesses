package io.github.eggloop.expression;

public interface ArithmeticExpression {

    ArithmeticExpression evaluate(Assignment assignment);

}