package io.github.eggloop.expression.relational;

public interface RelationalExpression<T> {

    DomainFunction<T> evaluate(Domain<T> domain);
}
