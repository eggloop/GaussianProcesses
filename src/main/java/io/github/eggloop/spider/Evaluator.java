package io.github.eggloop.spider;

import io.github.eggloop.expression.arithmetic.Assignment;
import io.github.eggloop.expression.arithmetic.Variable;
import io.github.eggloop.expression.relational.GreaterEqualTo;
import io.github.eggloop.expression.relational.LowerEqualTo;
import io.github.eggloop.stl.syntax.*;
import io.github.eggloop.stl.visitor.BooleanTemporalMonitoring;

import java.util.function.Function;
import java.util.function.Predicate;

public class Evaluator {

    public static boolean[] eval(Bean bean, Predicate<double[]> finallyCheck, Predicate<double[]> globallyCheck) {
        int eventuallySize = bean.getEventually().size();
        boolean[] result = new boolean[bean.getGlobally().size() + eventuallySize];
        for (int i = 0; i < eventuallySize; i++) {
            result[i] = finallyCheck.test(bean.getEventually().get(i));
        }
        for (int i = eventuallySize; i < result.length; i++) {
            result[i] = globallyCheck.test(bean.getGlobally().get(i - eventuallySize));
        }
        return result;
    }

    public static  Function<BooleanTemporalMonitoring, Predicate<double[]>> checkFinally(){
        Formula atomh = new Atom(new GreaterEqualTo(new Variable("X"), new Variable("h")));
        //X<=l
        Formula atoml = new Atom(new LowerEqualTo(new Variable("X"), new Variable("l"))); //X<=l
        //F[a,b] (X>=h and X<=l)
        Formula aFinally = new Finally(new Interval(new Variable("a"), new Variable("b")), new Conjunction(atomh, atoml));
        //G[a,b] (X>=h and X<=l)
        Function<double[], Assignment> ass = value -> {
            Assignment assignment = new Assignment();
            assignment.put("h", value[0]);
            assignment.put("l", value[1]);
            assignment.put("a", value[2]);
            assignment.put("b", value[3]);
            return assignment;
        };
        return getCheck(aFinally, ass);
    }

    public static  Function<BooleanTemporalMonitoring, Predicate<double[]>> checkGlobally(){
        Formula atomh = new Atom(new GreaterEqualTo(new Variable("X"), new Variable("h")));
        //X<=l
        Formula atoml = new Atom(new LowerEqualTo(new Variable("X"), new Variable("l"))); //X<=l
        //G[a,b] (X>=h and X<=l)
        Formula aGlobally = new Globally(new Interval(new Variable("a"), new Variable("b")), new Conjunction(atomh, atoml));

        Function<double[], Assignment> ass = value -> {
            Assignment assignment = new Assignment();
            assignment.put("h", value[0]);
            assignment.put("l", value[1]);
            assignment.put("a", value[2]);
            assignment.put("b", value[3]);
            return assignment;
        };
        return getCheck(aGlobally, ass);
    }

    private static Function<BooleanTemporalMonitoring, Predicate<double[]>> getCheck(Formula formula, Function<double[], Assignment> ass) {
        return booleanTemporalMonitoring -> construct(formula, booleanTemporalMonitoring, ass);
    }

    private static Predicate<double[]> construct(Formula formula, BooleanTemporalMonitoring monitor, Function<
            double[], Assignment> assignment) {
        return value -> formula.accept(monitor).evaluate(assignment.apply(value));
    }




}
