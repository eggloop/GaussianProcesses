package io.github.eggloop.expression.relational;

import io.github.eggloop.expression.arithmetic.Assignment;
import io.github.eggloop.expression.arithmetic.VariableException;

@FunctionalInterface
public interface DomainFunction<T> {

    T evaluate(Assignment assignment) throws VariableException;

}
