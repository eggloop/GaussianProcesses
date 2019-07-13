package io.github.eggloop.expression.relational;

import io.github.eggloop.expression.arithmetic.Assignment;

@FunctionalInterface
public interface DomainFunction<T> {

    T evaluate(Assignment assignment);

}
