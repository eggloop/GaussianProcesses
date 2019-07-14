package io.github.eggloop.stl.visitor;

import io.github.eggloop.expression.arithmetic.Assignment;
import io.github.eggloop.expression.arithmetic.Constant;
import io.github.eggloop.expression.arithmetic.Variable;
import io.github.eggloop.expression.relational.GreaterEqualTo;
import io.github.eggloop.stl.syntax.*;
import org.junit.jupiter.api.Test;

//FIXME
//TODO
class FormulaPrinterTest {

    @Test
    void testLogicOperatorToken() {
        FormulaVisitor<String> printer = new FormulaPrinter(new LogicOperatorToken());
        Formula prova = new Negation(new Finally(new Interval(new Variable("p"), new Constant(2)), new Negation(new Atom(new GreaterEqualTo(new Variable("X"), new Constant(5))))));
        Assignment assignment = new Assignment();
        assignment.put("p", 1);
        String accept = prova.accept(printer).evaluate(assignment);
        System.out.println(accept);
    }

    @Test
    void testLogicOperatorToken2() {
        FormulaVisitor<String> printer = new FormulaPrinter(new LogicOperatorToken());
        Formula prova = new Negation(new Finally(new Interval(new Variable("p"), new Constant(2)), new Negation(new Atom(new GreaterEqualTo(new Variable("X"), new Variable("q"))))));
        Assignment assignment = new Assignment();
        assignment.put("p", 1);
        assignment.put("q", 1);
        String accept = prova.accept(printer).evaluate(assignment);
        System.out.println(accept);
    }

    @Test
    void testStandardOperatorToken() {
        FormulaVisitor<String> printer = new FormulaPrinter(new StandardOperatorToken());
        Formula prova = new Negation(new Finally(new Interval(new Variable("A"), new Constant(2)), new Negation(new Atom(new GreaterEqualTo(new Variable("X"), new Constant(5))))));
        Assignment assignment = new Assignment();
        assignment.put("A", 10);
        String accept = prova.accept(printer).evaluate(assignment);
        System.out.println(accept);
    }
}