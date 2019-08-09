package io.github.eggloop.expression.arithmetic;

import io.github.eggloop.expression.relational.DomainFunction;

import java.util.function.Predicate;

public interface ArithmeticExpression {

    DomainFunction<Double> compile();

    DomainFunction<String> print();

    DomainFunction<Predicate<Assignment>> logicalImplication();

}