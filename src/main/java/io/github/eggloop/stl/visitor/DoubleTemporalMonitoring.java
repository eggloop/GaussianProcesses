//package io.github.eggloop.stl.visitor;
//
//import io.github.eggloop.expression.relational.DomainFunction;
//import io.github.eggloop.expression.relational.DoubleDomain;
//import io.github.eggloop.stl.syntax.*;
//import io.github.eggloop.trajectories.Trajectory;
//
//public class DoubleTemporalMonitoring implements FormulaVisitor<Double> {
//
//    private static final DoubleDomain DOMAIN = new DoubleDomain();
//    private Trajectory trajectory;
//    private int currentState = 0;
//
//    public DoubleTemporalMonitoring(Trajectory trajectory) {
//        this.trajectory = trajectory;
//    }
//
//    @Override
//    public DomainFunction<Double> visit(Negation formula) {
//        return assignment -> DOMAIN.negation(formula.getArgument().accept(this).evaluate(assignment));
//    }
//
//    @Override
//    public DomainFunction<Double> visit(Atom formula) {
//        return assignment -> {
//            for (int i = 0; i < trajectory.getVariables().length; i++) {
//                assignment.put(trajectory.getVariables()[i], trajectory.getValues()[i][currentState]);
//            }
//            return formula.getExpression().compile(DOMAIN).evaluate(assignment);
//        };
//
//    }
//
//    @Override
//    public DomainFunction<Double> visit(Disjunction formula) {
//        return assignment -> {
//            double left = formula.getLeft().accept(this).evaluate(assignment);
//            double right = formula.getRight().accept(this).evaluate(assignment);
//            return DOMAIN.disjunction(left, right);
//        };
//    }
//
//
//    @Override
//    public DomainFunction<Double> visit(Conjunction formula) {
//        return assignment -> {
//            double left = formula.getLeft().accept(this).evaluate(assignment);
//            double right = formula.getRight().accept(this).evaluate(assignment);
//            return DOMAIN.conjunction(left, right);
//        };
//    }
//
//    @Override
//    public DomainFunction<Double> visit(Finally formula) {
//        return null;
//    }
//
//    @Override
//    public DomainFunction<Double> visit(Globally formula) {
//        return null;
//    }
//
//}
