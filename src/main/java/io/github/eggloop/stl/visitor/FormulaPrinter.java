package io.github.eggloop.stl.visitor;

import io.github.eggloop.expression.arithmetic.Parameter;
import io.github.eggloop.expression.relational.DomainFunction;
import io.github.eggloop.stl.syntax.*;

public class FormulaPrinter implements FormulaVisitor<String> {

    private OperatorToken operatorToken;

    public FormulaPrinter(OperatorToken operatorToken) {
        this.operatorToken = operatorToken;
    }

    @Override
    public DomainFunction<String> visit(Negation formula) {
        return assignment -> SyntaxUtils.toStringUnaryFormula(operatorToken.negation(), formula.getArgument().accept(this).evaluate(assignment));
    }

    @Override
    public DomainFunction<String> visit(Atom formula) {
        return assignment -> formula.toString(); //TODO
    }

    @Override
    public DomainFunction<String> visit(Disjunction formula) {
        return assignment -> SyntaxUtils.toStringBinaryFormula(formula.getLeft().accept(this).evaluate(assignment), operatorToken.disjunction(), formula.getRight().accept(this).evaluate(assignment));
    }

    @Override
    public DomainFunction<String> visit(Conjunction formula) {
        return assignment -> SyntaxUtils.toStringBinaryFormula(formula.getLeft().accept(this).evaluate(assignment), operatorToken.conjunction(), formula.getRight().accept(this).evaluate(assignment));
    }

    @Override
    public DomainFunction<String> visit(Finally formula) {
        return assignment -> {
            Interval interval = formula.getInterval();
            String intervalToString = "[" + interval.getLeft().accept(this).evaluate(assignment) + "," + interval.getRight().accept(this).evaluate(assignment) + "]";
            return "F_" + intervalToString + formula.getArgument().accept(this).evaluate(assignment);
        };
    }

    @Override
    public DomainFunction<String> visit(Globally formula) {
        return assignment -> "G_" + formula.getInterval().toString() + formula.getArgument().accept(this).evaluate(assignment);
    }

    @Override
    public DomainFunction<String> visit(Historically formula) {
        return assignment -> "H_" + formula.getInterval().toString() + formula.getArgument().accept(this).evaluate(assignment);
    }

    @Override
    public DomainFunction<String> visit(Parameter formula) {
        return formula.print();
    }

}
