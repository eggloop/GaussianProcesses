package io.github.eggloop.stl.visitor;

import io.github.eggloop.expression.relational.BooleanDomain;
import io.github.eggloop.expression.relational.DomainFunction;
import io.github.eggloop.stl.syntax.*;
import io.github.eggloop.trajectories.Trajectory;
import io.github.eggloop.utils.ArraysUtility;

import java.util.function.IntPredicate;

public class BooleanTemporalMonitoring implements FormulaVisitor<Boolean> {

    private static final BooleanDomain DOMAIN = new BooleanDomain();
    private Trajectory trajectory;
    private int currentState;

    public BooleanTemporalMonitoring(Trajectory trajectory) {
        this(trajectory, 0);
    }

    private BooleanTemporalMonitoring(Trajectory trajectory, int currentState) {
        this.trajectory = trajectory;
        this.currentState = currentState;
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
        return assignment -> {
            double[] times = trajectory.getTimes();
            double leftInterval = formula.getInterval().getLeft().compile().evaluate(assignment);
            double rightInterval = formula.getInterval().getRight().compile().evaluate(assignment);
            IntPredicate predicate = i -> formula.getArgument().accept(new BooleanTemporalMonitoring(trajectory, i)).evaluate(assignment);
            return ArraysUtility.getPositionOfOrderedArrayBetween(times, leftInterval, rightInterval).
                    anyMatch(predicate);
        };
    }

    @Override
    public DomainFunction<Boolean> visit(Globally formula) {
        return assignment -> {
            double[] times = trajectory.getTimes();
            double leftInterval = formula.getInterval().getLeft().compile().evaluate(assignment);
            double rightInterval = formula.getInterval().getRight().compile().evaluate(assignment);
            IntPredicate predicate = i -> formula.getArgument().accept(new BooleanTemporalMonitoring(trajectory, i)).evaluate(assignment);
            return ArraysUtility.getPositionOfOrderedArrayBetween(times, leftInterval, rightInterval).
                    noneMatch(predicate.negate());
        };
    }
}