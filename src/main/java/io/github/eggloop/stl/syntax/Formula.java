package io.github.eggloop.stl.syntax;

import io.github.eggloop.expression.relational.DomainFunction;
import io.github.eggloop.stl.visitor.FormulaVisitor;

public interface Formula {

    <T> DomainFunction<T> accept(FormulaVisitor<T> visitor);
}
