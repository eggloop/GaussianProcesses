package io.github.eggloop.expression.relational;

public interface RelationalExpression<T> {

    T evaluate(Domain<T> domain);
}
