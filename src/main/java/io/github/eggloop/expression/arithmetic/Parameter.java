package io.github.eggloop.expression.arithmetic;

import io.github.eggloop.expression.relational.DomainFunction;
import io.github.eggloop.stl.visitor.FormulaVisitor;

public interface Parameter {
    <T> DomainFunction<T> accept(FormulaVisitor<T> visitor);

    DomainFunction<String> print();
}
