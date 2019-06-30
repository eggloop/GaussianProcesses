package io.github.eggloop.expression.relational;

public interface RelationalExpression<T> {

    DomainFunction<T> compile(Domain<T> domain);
}
