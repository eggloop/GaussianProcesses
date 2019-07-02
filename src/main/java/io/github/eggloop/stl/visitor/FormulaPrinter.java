package io.github.eggloop.stl.visitor;

import io.github.eggloop.stl.syntax.*;

public class FormulaPrinter implements FormulaVisitor<String> {

    private OperatorToken operatorToken;

    public FormulaPrinter(OperatorToken operatorToken) {
        this.operatorToken = operatorToken;
    }

    @Override
    public String visit(Negation formula) {
        return SyntaxUtils.toStringUnaryFormula(operatorToken.negation(), formula.getArgument().accept(this));
    }

    @Override
    public String visit(Atom formula) {
        return formula.toString();
    }

    @Override
    public String visit(Disjunction formula) {
        return SyntaxUtils.toStringBinaryFormula(formula.getLeft().accept(this), operatorToken.disjunction(), formula.getRight().accept(this));
    }

    @Override
    public String visit(Conjunction formula) {
        return SyntaxUtils.toStringBinaryFormula(formula.getLeft().accept(this), operatorToken.conjunction(), formula.getRight().accept(this));
    }

    @Override
    public String visit(Finally formula) {
        return "F_" + formula.getInterval().toString() + formula.getArgument().accept(this);
    }

    @Override
    public String visit(Globally formula) {
        return "G_" + formula.getInterval().toString() + formula.getArgument().accept(this);
    }
}
