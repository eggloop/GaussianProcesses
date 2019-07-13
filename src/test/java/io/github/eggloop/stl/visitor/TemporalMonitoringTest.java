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
        Formula prova = new Finally(new Interval(new Variable("a"), new Variable("b")), new Atom(new GreaterEqualTo(new Variable("X"), new Variable("k"))));
//        String actualJSON = "{\"variables\":[\"X\",\"Y\"],\"values\":[[1.0,2.0],[2.0,3.0]],\"time\":[1.0,2.0]}";
        String actualJSON = "{\"time\": [0.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7, 1.8, 1.9, 2.0, 2.1, 2.2, 2.3, 2.4, 2.5, 2.6, 2.7, 2.8, 2.9, 3.0, 3.1, 3.2, 3.3, 3.4, 3.5, 3.6, 3.7, 3.8, 3.9, 4.0, 4.1, 4.2, 4.3, 4.4, 4.5, 4.6, 4.7, 4.8, 4.9, 5.0, 5.1, 5.2, 5.3, 5.4, 5.5, 5.6, 5.7, 5.8, 5.9, 6.0, 6.1, 6.2, 6.3, 6.4, 6.5, 6.6, 6.7, 6.8, 6.9, 7.0, 7.1, 7.2, 7.3, 7.4, 7.5, 7.6, 7.7, 7.8, 7.9, 8.0, 8.1, 8.2, 8.3, 8.4, 8.5, 8.6, 8.7, 8.8, 8.9, 9.0, 9.1, 9.2, 9.3, 9.4, 9.5, 9.6, 9.7, 9.8, 9.9], \"values\": [[0.145277, 0.157458, 0.168991, 0.179607, 0.189053, 0.197104, 0.203578, 0.208344, 0.211339, 0.212568, 0.212115, 0.210141, 0.206881, 0.202645, 0.197801, 0.192769, 0.188008, 0.183992, 0.181201, 0.180099, 0.181111, 0.184612, 0.190903, 0.200197, 0.212605, 0.228126, 0.246635, 0.267887, 0.291511, 0.317021, 0.343825, 0.371244, 0.398533, 0.424909, 0.449577, 0.471764, 0.49075, 0.505899, 0.516687, 0.52272, 0.523759, 0.51972, 0.510683, 0.496884, 0.478699, 0.456626, 0.431262, 0.403272, 0.373357, 0.342229, 0.310581, 0.279058, 0.248242, 0.218633, 0.190641, 0.164579, 0.140666, 0.119032, 0.0997233, 0.0827154, 0.0679255, 0.0552252, 0.0444527, 0.0354256, 0.0279507, 0.0218336, 0.0168855, 0.0129289, 0.0098009, 0.00735575, 0.00546569, 0.00402087, 0.00292855, 0.00211175, 0.00150761, 0.00106559, 0.00074568, 0.000516618, 0.00035436, 0.000240645, 0.000161795, 0.000107699, 7.09763e-05, 4.63099e-05, 2.99151e-05, 1.91322e-05, 1.21142e-05, 7.59423e-06, 4.71334e-06, 2.89622e-06, 1.76194e-06, 1.06122e-06, 6.3282e-07, 3.73603e-07, 2.18373e-07, 1.2637e-07, 7.24009e-08, 4.10679e-08, 2.30631e-08, 1.2823e-08]], \"variables\": [\"X\"]}";
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

        long l = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            double v = Math.random() * 10;
            double vv = v + Math.random() * (10 - v);
            eval.apply(new double[]{0.2*Math.random(), v, vv});
        }
        long time = System.currentTimeMillis() - l;
        System.out.println(time);
    }
}