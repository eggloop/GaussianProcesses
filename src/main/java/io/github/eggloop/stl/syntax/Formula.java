package io.github.eggloop.stl.syntax;

import io.github.eggloop.common.StringHashCode;
import io.github.eggloop.expression.relational.DomainFunction;
import io.github.eggloop.stl.visitor.FormulaVisitor;

public interface Formula extends StringHashCode {

    <T> DomainFunction<T> accept(FormulaVisitor<T> visitor);

    @Override
    default String stringHashCode() {
        return toString();
    }
}
