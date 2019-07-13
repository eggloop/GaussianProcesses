package io.github.eggloop.stl.visitor;

import io.github.eggloop.expression.arithmetic.Assignment;
import io.github.eggloop.expression.arithmetic.Variable;
import io.github.eggloop.expression.relational.GreaterEqualTo;
import io.github.eggloop.stl.syntax.Atom;
import io.github.eggloop.stl.syntax.Finally;
import io.github.eggloop.stl.syntax.Formula;
import io.github.eggloop.stl.syntax.Interval;
import io.github.eggloop.trajectories.Trajectory;
import io.github.eggloop.trajectories.TrajectoryFactory;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

//FIXME
class TemporalMonitoringTest {

    @Test
    void name() throws ParseException {
        Formula prova = new Finally(new Interval(new Variable("a"),new Variable("b")),new Atom(new GreaterEqualTo(new Variable("X"), new Variable("k"))));
        String actualJSON = "{\"variables\":[\"X\",\"Y\"],\"values\":[[1.0,2.0],[2.0,3.0]],\"time\":[1.0,2.0]}";

        Trajectory actualTrajectory = TrajectoryFactory.fromJSON(actualJSON);
        BooleanTemporalMonitoring monitoring = new BooleanTemporalMonitoring(actualTrajectory);

        Function<double[], Assignment> ass = value -> {
            Assignment assignment = new Assignment();
            assignment.put("k", value[0]);
            assignment.put("a", value[1]);
            assignment.put("b", value[2]);
            return assignment;
        };

        Function<double[], Boolean> eval = value -> prova.accept(monitoring).evaluate(ass.apply(value));

        System.out.println(eval.apply(new double[]{0.,1.,2.}));
//        System.out.println(eval.apply(9.));
//        System.out.println(eval.apply(8.));
//        System.out.println(eval.apply(10.));

    }
}