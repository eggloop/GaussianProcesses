package io.github.eggloop.stl.visitor;

import io.github.eggloop.expression.arithmetic.ArithmeticExpression;
import io.github.eggloop.expression.arithmetic.Assignment;
import io.github.eggloop.expression.arithmetic.Variable;
import io.github.eggloop.expression.relational.DomainFunction;
import io.github.eggloop.stl.syntax.*;

import java.util.function.Predicate;

public class LogicalImplication implements FormulaVisitor<Predicate<Assignment>> {
    @Override
    public DomainFunction<Predicate<Assignment>> visit(Negation formula) {
        return assignment -> formula.getArgument().accept(this).evaluate(assignment).negate();
    }

    @Override
    public DomainFunction<Predicate<Assignment>> visit(Atom formula) {
        return formula.getExpression().logicalImplication();
    }

    @Override
    public DomainFunction<Predicate<Assignment>> visit(Disjunction formula) {
        return assignment -> formula.getLeft().accept(this).evaluate(assignment).or(formula.getRight().accept(this).evaluate(assignment));
    }

    @Override
    public DomainFunction<Predicate<Assignment>> visit(Conjunction formula) {
        return assignment -> formula.getLeft().accept(this).evaluate(assignment).and(formula.getRight().accept(this).evaluate(assignment));

    }

    @Override
    public DomainFunction<Predicate<Assignment>> visit(Finally formula) {
        ArithmeticExpression left = formula.getInterval().getLeft();
        ArithmeticExpression right = formula.getInterval().getRight();
        if (left instanceof Variable && right instanceof Variable) {
            Variable leftVariable = (Variable) left;
            Variable rightVariable = (Variable) right;
            return assignment -> inner -> inner.get(leftVariable.toString()) <= leftVariable.compile().evaluate(assignment) &&
                    inner.get(rightVariable.toString()) >= rightVariable.compile().evaluate(assignment) && formula.getArgument().accept(this).evaluate(assignment).test(inner);
        }
        return assignment -> inner -> false;
    }

    @Override
    public DomainFunction<Predicate<Assignment>> visit(Globally formula) {
        ArithmeticExpression left = formula.getInterval().getLeft();
        ArithmeticExpression right = formula.getInterval().getRight();
        if (left instanceof Variable && right instanceof Variable) {
            Variable leftVariable = (Variable) left;
            Variable rightVariable = (Variable) right;
            return assignment -> inner -> inner.get(leftVariable.toString()) >= leftVariable.compile().evaluate(assignment) &&
                    inner.get(rightVariable.toString()) <= rightVariable.compile().evaluate(assignment) && formula.getArgument().accept(this).evaluate(assignment).test(inner);
        }
        return assignment -> inner -> false;
    }
}
