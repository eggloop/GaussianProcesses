package io.github.eggloop.stl.visitor;

import io.github.eggloop.expression.arithmetic.Assignment;
import io.github.eggloop.expression.arithmetic.Variable;
import io.github.eggloop.expression.relational.GreaterThan;
import io.github.eggloop.stl.syntax.*;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

class LogicalImplicationTest {

    @Test
    void name() {
        Variable x = new Variable("X");
        Variable y = new Variable("Y");
        Finally aFinally = new Finally(new Interval(new Variable("a"), new Variable("b")), new Atom(new GreaterThan(x, y)));
        LogicalImplication logicalImplication = new LogicalImplication();
        Assignment ass = new Assignment();
        ass.put("Y", 2);
        ass.put("a", 1);
        ass.put("b", 3);

        Predicate<Assignment> evaluate = aFinally.accept(logicalImplication).evaluate(ass);
        Assignment ass2 = new Assignment();
        ass2.put("Y", 2);
        ass2.put("a", 1);
        ass2.put("b", 4);

        System.out.println(evaluate.test(ass2));
    }

    @Test
    void name2() {
        GreaterThan x = new GreaterThan(new Variable("X"), new Variable("x"));
        GreaterThan y = new GreaterThan(new Variable("Y"), new Variable("y"));
        Conjunction conjunction = new Conjunction(new Atom(x), new Atom(y));
        Formula formula = new Globally(new Interval(new Variable("t1"), new Variable("t2")), conjunction);

        Assignment original = new Assignment();
        original.put("x", 3);
        original.put("y", 4);
        original.put("t1", 1);
        original.put("t2", 2);

        Predicate<Assignment> predicate = formula.accept(new LogicalImplication()).evaluate(original);

        Assignment newAssignment = new Assignment();
        newAssignment.put("x", 2);
        newAssignment.put("y", 4);
        newAssignment.put("t1", 1.5);
        newAssignment.put("t2", 2);

        boolean test = predicate.test(newAssignment);
        System.out.println(test);
    }
}