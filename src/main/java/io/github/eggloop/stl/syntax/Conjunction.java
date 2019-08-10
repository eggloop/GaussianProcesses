package io.github.eggloop.stl.syntax;

import io.github.eggloop.expression.relational.DomainFunction;

public class Conjunction implements Formula {

    private Formula left;
    private Formula right;

    public Conjunction(Formula left, Formula right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return SyntaxUtils.toStringBinaryFormula(left.toString(), "&", right.toString());
    }

    @Override
    public <T> DomainFunction<T> accept(FormulaVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public Formula getLeft() {
        return left;
    }

    public Formula getRight() {
        return right;
    }
}
