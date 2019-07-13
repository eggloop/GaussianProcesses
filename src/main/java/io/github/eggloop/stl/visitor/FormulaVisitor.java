package io.github.eggloop.stl.visitor;

import io.github.eggloop.expression.relational.DomainFunction;
import io.github.eggloop.stl.syntax.*;

public interface FormulaVisitor<T> {

    DomainFunction<T> visit(Negation formula);

    DomainFunction<T> visit(Atom formula);

    DomainFunction<T> visit(Disjunction formula);

    DomainFunction<T> visit(Conjunction formula);

    DomainFunction<T> visit(Finally formula);

    DomainFunction<T> visit(Globally formula);

}