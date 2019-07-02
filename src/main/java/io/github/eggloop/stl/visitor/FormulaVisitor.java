package io.github.eggloop.stl.visitor;

import io.github.eggloop.stl.syntax.*;

public interface FormulaVisitor<T> {

    T visit(Negation formula);

    T visit(Atom formula);

    T visit(Disjunction formula);

    T visit(Conjunction formula);

    T visit(Finally formula);

    T visit(Globally formula);
}
