package io.github.eggloop.stl.visitor;

import io.github.eggloop.stl.syntax.STL;

public interface FormulaVisitor {

    void negation(STL formula);

    void atom(STL formula);

    void disjunction(STL left, STL right);

    void conjunction(STL left, STL right);
}
