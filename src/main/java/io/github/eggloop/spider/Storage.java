package io.github.eggloop.spider;

import io.github.eggloop.expression.arithmetic.Assignment;
import io.github.eggloop.stl.syntax.Formula;
import io.github.eggloop.stl.visitor.LogicalImplication;

import java.util.function.Function;
import java.util.function.Predicate;

public class Storage {

    private Predicate<Assignment> formulaPredicate = s -> false;

    public synchronized boolean eval(Formula formula, Function<double[], Assignment> toAssignment, double[] parameters, Predicate<Assignment> monitor) {
        if (formulaPredicate.test(toAssignment.apply(parameters))) {
            System.out.println("A");
            return true;
        }
        if (monitor.test(toAssignment.apply(parameters))) {
            formulaPredicate = formula.accept(new LogicalImplication()).evaluate(toAssignment.apply(parameters));
//            System.out.println("B");
            return true;
        }
        return false;
    }
}
