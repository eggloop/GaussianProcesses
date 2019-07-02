package io.github.eggloop.stl.syntax;

import io.github.eggloop.stl.visitor.FormulaVisitor;

public interface Formula {

    <T> T accept(FormulaVisitor<T> visitor);
}
