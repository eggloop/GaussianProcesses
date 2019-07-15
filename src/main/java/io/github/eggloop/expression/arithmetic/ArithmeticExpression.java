package io.github.eggloop.expression.arithmetic;

import io.github.eggloop.expression.relational.DomainFunction;

public interface ArithmeticExpression {

    DomainFunction<Double> compile();

    DomainFunction<String> print();


}