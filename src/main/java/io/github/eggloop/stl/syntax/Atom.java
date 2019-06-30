package io.github.eggloop.stl.syntax;

import io.github.eggloop.expression.relational.RelationalExpression;

public class Atom implements STL {

    private RelationalExpression expression;

    public Atom(RelationalExpression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return expression.toString();
    }

    @Override
    public String toLogicString() {
        return toString();
    }
}
