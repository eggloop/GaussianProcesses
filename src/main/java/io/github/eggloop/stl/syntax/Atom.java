package io.github.eggloop.stl.syntax;

import io.github.eggloop.expression.arithmetic.Assignment;
import io.github.eggloop.expression.relational.DomainFunction;
import io.github.eggloop.expression.relational.RelationalExpression;
import io.github.eggloop.stl.visitor.FormulaVisitor;

public class Atom implements Formula {

    private RelationalExpression expression;

    public Atom(RelationalExpression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return expression.print().evaluate(new Assignment());
    }

    @Override
    public <T> DomainFunction<T> accept(FormulaVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public RelationalExpression getExpression() {
        return expression;
    }
}
