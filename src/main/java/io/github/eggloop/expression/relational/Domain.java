package io.github.eggloop.expression.relational;

import io.github.eggloop.expression.arithmetic.ArithmeticExpression;

interface Domain<T> {
    DomainFunction<T> lowerEqualTo(ArithmeticExpression left, ArithmeticExpression right);

    DomainFunction<T> lowerThan(ArithmeticExpression left, ArithmeticExpression right);

    DomainFunction<T> greaterEqualTo(ArithmeticExpression left, ArithmeticExpression right);

    DomainFunction<T> greaterThan(ArithmeticExpression left, ArithmeticExpression right);

    DomainFunction<T> equalTo(ArithmeticExpression left, ArithmeticExpression right);
}
