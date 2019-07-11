package io.github.eggloop.stl.visitor;

import io.github.eggloop.expression.arithmetic.Parameter;
import io.github.eggloop.expression.relational.BooleanDomain;
import io.github.eggloop.expression.relational.DomainFunction;
import io.github.eggloop.stl.syntax.*;
import io.github.eggloop.trajectories.Trajectory;

public class BooleanTemporalMonitoring implements FormulaVisitor<Boolean> {

    private static final BooleanDomain DOMAIN = new BooleanDomain();
    private Trajectory trajectory;
    private int currentState = 0;

    public BooleanTemporalMonitoring(Trajectory trajectory) {
        this.trajectory = trajectory;
    }

    @Override
    public DomainFunction<Boolean> visit(Negation formula) {
        return assignment -> DOMAIN.negation(formula.getArgument().accept(this).evaluate(assignment));
    }

    @Override
    public DomainFunction<Boolean> visit(Atom formula) {
        return assignment -> {
            for (int i = 0; i < trajectory.getVariables().length; i++) {
                assignment.put(trajectory.getVariables()[i], trajectory.getValues()[i][currentState]);
            }
            return formula.getExpression().compile(DOMAIN).evaluate(assignment);
        };

    }

    @Override
    public DomainFunction<Boolean> visit(Disjunction formula) {
        return assignment -> {
            boolean left = formula.getLeft().accept(this).evaluate(assignment);
            boolean right = formula.getRight().accept(this).evaluate(assignment);
            return DOMAIN.disjunction(left, right);
        };
    }


    @Override
    public DomainFunction<Boolean> visit(Conjunction formula) {
        return assignment -> {
            boolean left = formula.getLeft().accept(this).evaluate(assignment);
            boolean right = formula.getRight().accept(this).evaluate(assignment);
            return DOMAIN.conjunction(left, right);
        };
    }

    @Override
    public DomainFunction<Boolean> visit(Finally formula) {
        return null;
    }

    @Override
    public DomainFunction<Boolean> visit(Globally formula) {
        return null;
    }

    @Override
    public DomainFunction<Boolean> visit(Historically formula) {
        return null;
    }

    @Override
    public DomainFunction<Boolean> visit(Parameter formula) {
        return null;
    }

}
