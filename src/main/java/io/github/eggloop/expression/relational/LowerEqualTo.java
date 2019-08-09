package io.github.eggloop.expression.relational;

import io.github.eggloop.expression.arithmetic.ArithmeticExpression;
import io.github.eggloop.expression.arithmetic.Assignment;
import io.github.eggloop.stl.syntax.SyntaxUtils;

import java.util.function.Predicate;

public class LowerEqualTo implements RelationalExpression {

    private ArithmeticExpression left;
    private ArithmeticExpression right;

    public LowerEqualTo(ArithmeticExpression left, ArithmeticExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public DomainFunction compile(Domain domain) {
        return domain.lowerEqualTo(left, right);
    }

    @Override
    public DomainFunction<String> print() {
        return assignment -> SyntaxUtils.toStringBinaryFormula(left.print().evaluate(assignment), "<=", right.print().evaluate(assignment));
    }
    @Override
    public DomainFunction<Predicate<Assignment>> logicalImplication() {
        return assignment -> left.logicalImplication().evaluate(assignment).negate().and(right.logicalImplication().evaluate(assignment));
    }


    @Override
    public String toString() {
        return SyntaxUtils.toStringBinaryFormula(left.toString(), "<=", right.toString());
    }
}
