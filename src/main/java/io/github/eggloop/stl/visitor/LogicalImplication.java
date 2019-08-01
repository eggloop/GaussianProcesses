package io.github.eggloop.stl.visitor;

import io.github.eggloop.expression.arithmetic.Assignment;
import io.github.eggloop.expression.relational.DomainFunction;
import io.github.eggloop.stl.syntax.*;

import java.util.function.Predicate;

public class LogicalImplication implements FormulaVisitor<Predicate<Assignment>> {
    @Override
    public DomainFunction<Predicate<Assignment>> visit(Negation formula) {
        return null;
    }

    @Override
    public DomainFunction<Predicate<Assignment>> visit(Atom formula) {
        return null;
    }

    @Override
    public DomainFunction<Predicate<Assignment>> visit(Disjunction formula) {
        return null;
    }

    @Override
    public DomainFunction<Predicate<Assignment>> visit(Conjunction formula) {
        return null;
    }

    @Override
    public DomainFunction<Predicate<Assignment>> visit(Finally formula) {
        return null;
    }

    @Override
    public DomainFunction<Predicate<Assignment>> visit(Globally formula) {
        return null;
    }
}
