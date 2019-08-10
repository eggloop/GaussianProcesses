package io.github.eggloop.expression.arithmetic;

import io.github.eggloop.expression.relational.DomainFunction;

public interface ArithmeticExpressionVisitor<T> {

    DomainFunction<T> visit(Multiplication expression);

    DomainFunction<T> visit(Division expression);

    DomainFunction<T> visit(Minus expression);

    DomainFunction<T> visit(Subtraction expression);

    DomainFunction<T> visit(Constant expression);

    DomainFunction<T> visit(Addition expression);

    DomainFunction<T> visit(Variable expression);
}
