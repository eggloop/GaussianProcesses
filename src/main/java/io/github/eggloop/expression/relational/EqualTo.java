package io.github.eggloop.expression.relational;

import io.github.eggloop.expression.arithmetic.ArithmeticExpression;
import io.github.eggloop.expression.arithmetic.Assignment;
import io.github.eggloop.stl.syntax.SyntaxUtils;

import java.util.function.Predicate;

public class EqualTo implements RelationalExpression {

    private ArithmeticExpression left;
    private ArithmeticExpression right;

    public EqualTo(ArithmeticExpression left, ArithmeticExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public DomainFunction compile(Domain domain) {
        return domain.equalTo(left, right);
    }

    @Override
    public DomainFunction<String> print() {
        return assignment -> SyntaxUtils.toStringBinaryFormula(left.print().evaluate(assignment), "==", right.print().evaluate(assignment));
    }

    @Override
    public DomainFunction<Predicate<Assignment>> logicalImplication() {
        return assignment -> inner -> false;
    }


    @Override
    public String toString() {
        return SyntaxUtils.toStringBinaryFormula(left.toString(), "==", right.toString());
    }
}
