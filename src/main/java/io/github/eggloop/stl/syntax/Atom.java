package io.github.eggloop.stl.syntax;

import io.github.eggloop.expression.relational.DomainFunction;
import io.github.eggloop.expression.relational.RelationalExpression;

public class Atom implements Formula {

    private RelationalExpression expression;

    public Atom(RelationalExpression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return expression.toString();
    }

    @Override
    public <T> DomainFunction<T> accept(FormulaVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public RelationalExpression getExpression() {
        return expression;
    }
}
