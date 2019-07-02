package io.github.eggloop.stl.syntax;

import io.github.eggloop.stl.visitor.FormulaVisitor;

public class Disjunction implements Formula {

    private Formula left;
    private Formula right;

    public Disjunction(Formula left, Formula right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return SyntaxUtils.toStringBinaryFormula(left.toString(), "|", right.toString());
    }


    public String toLogicString() {
        return SyntaxUtils.toStringBinaryFormula(left.toString(), "∨", right.toString());
    }

    @Override
    public <T> T accept(FormulaVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public Formula getLeft() {
        return left;
    }

    public Formula getRight() {
        return right;
    }
}
