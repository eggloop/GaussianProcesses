package io.github.eggloop.stl.visitor;

import io.github.eggloop.expression.arithmetic.Constant;
import io.github.eggloop.expression.arithmetic.Variable;
import io.github.eggloop.expression.relational.GreaterEqualTo;
import io.github.eggloop.stl.syntax.*;
import org.junit.jupiter.api.Test;

class FormulaPrinterTest {

    @Test
    void name() {
        FormulaPrinter printer = new FormulaPrinter(new LogicOperatorToken());
        Formula prova = new Negation(new Finally(new Interval(1, 2), new Negation(new Atom(new GreaterEqualTo(new Variable("X"), new Constant(5))))));

        String accept = prova.accept(printer);
        System.out.println(accept);
    }
}