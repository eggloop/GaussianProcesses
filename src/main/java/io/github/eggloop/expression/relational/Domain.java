package io.github.eggloop.expression.relational;

import io.github.eggloop.expression.arithmetic.ArithmeticExpression;

interface Domain<T> {
    DomainFunction<T> lowerEqual(ArithmeticExpression left, ArithmeticExpression right);

    DomainFunction<T> lower(ArithmeticExpression left, ArithmeticExpression right);

    DomainFunction<T> greaterEqual(ArithmeticExpression left, ArithmeticExpression right);

    DomainFunction<T> greater(ArithmeticExpression left, ArithmeticExpression right);

    DomainFunction<T> equal(ArithmeticExpression left, ArithmeticExpression right);
}
