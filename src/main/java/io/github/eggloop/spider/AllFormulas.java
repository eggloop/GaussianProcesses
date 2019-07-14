package io.github.eggloop.spider;

import io.github.eggloop.expression.arithmetic.Assignment;
import io.github.eggloop.expression.arithmetic.Variable;
import io.github.eggloop.expression.relational.GreaterEqualTo;
import io.github.eggloop.stl.syntax.*;
import io.github.eggloop.stl.visitor.BooleanTemporalMonitoring;
import io.github.eggloop.stl.visitor.FormulaPrinter;
import io.github.eggloop.stl.visitor.LogicOperatorToken;
import io.github.eggloop.trajectories.Trajectory;
import io.github.eggloop.trajectories.TrajectoryFactory;
import io.github.eggloop.utils.FileUtils;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

public class AllFormulas {

    private void getTrajectory() throws IOException, ParseException {
        String location = "/Users/lauretta/git/eggloop/scripts/traccia.json";
        String jsonTrajectory = FileUtils.readFileToString(location);
        Trajectory trajectory = TrajectoryFactory.fromJSON(jsonTrajectory);
        double[] times = trajectory.getTimes();
        double[] space = IntStream.range(-1, 3).mapToDouble(s -> 0.1 * s).toArray();
        List<double[]> intervals = new ArrayList<>();
        for (int i = 0; i < times.length; i++) {
            double initTime = times[i];
            for (int j = i; j < times.length; j++) {
                intervals.add(new double[]{initTime, times[j]});
            }
        }
//        double[] uniqueSpace = Arrays.stream(array).distinct().toArray(String[]::new);
        BooleanTemporalMonitoring booleanTemporalMonitoring = new BooleanTemporalMonitoring(trajectory);
        FormulaPrinter formulaPrinter = new FormulaPrinter(new LogicOperatorToken());

        Formula provaf = new Finally(new Interval(new Variable("a"), new Variable("b")), new Atom(new GreaterEqualTo(new Variable("X"), new Variable("k"))));
        Formula provag = new Globally(new Interval(new Variable("a"), new Variable("b")), new Atom(new GreaterEqualTo(new Variable("X"), new Variable("k"))));

        Function<double[], Assignment> ass = value -> {
            Assignment assignment = new Assignment();
            assignment.put("k", value[0]);
            assignment.put("a", value[1]);
            assignment.put("b", value[2]);
            return assignment;
        };


        Function<double[], Boolean> eval = value -> provag.accept(booleanTemporalMonitoring).evaluate(ass.apply(value));
        Function<double[], String> printer = value -> provag.accept(formulaPrinter).evaluate(ass.apply(value));
        for (double[] interval : intervals) {
            for (double spaceValue : space) {
                double[] params = {spaceValue, interval[0], interval[1]};
                System.out.println(printer.apply(params) + " " + eval.apply(params) + "  k=" + spaceValue);
            }
        }

    }

    public static void main(String[] args) throws IOException, ParseException {
        AllFormulas f = new AllFormulas();
        f.getTrajectory();
    }
}
