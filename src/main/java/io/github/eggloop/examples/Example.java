package io.github.eggloop.examples;

import io.github.eggloop.expression.arithmetic.ArithmeticExpression;
import io.github.eggloop.expression.arithmetic.Constant;
import io.github.eggloop.expression.arithmetic.Variable;
import io.github.eggloop.expression.relational.BooleanDomain;
import io.github.eggloop.expression.relational.GreaterEqualTo;
import io.github.eggloop.expression.relational.RelationalExpression;
import io.github.eggloop.stl.syntax.Atom;
import io.github.eggloop.stl.syntax.Disjunction;
import io.github.eggloop.stl.syntax.Formula;

public class Example {

    public static void main(String[] args) {
        ArithmeticExpression X = new Variable("X");
        ArithmeticExpression Y = new Variable("Y");
        ArithmeticExpression C = new Constant(2);

        RelationalExpression leftAtom = new GreaterEqualTo(X, C);
        RelationalExpression rightAtom = new GreaterEqualTo(X, Y);

        Formula formula = new Disjunction(new Atom(leftAtom), new Atom(rightAtom));
        BooleanDomain domain = new BooleanDomain();
    }

}

