package io.github.eggloop.stl.syntax;

import io.github.eggloop.common.StringHashCode;
import io.github.eggloop.expression.relational.DomainFunction;

public interface Formula extends StringHashCode {

    <T> DomainFunction<T> accept(FormulaVisitor<T> visitor);

    @Override
    default String hash() {
        return toString();
    }
}
