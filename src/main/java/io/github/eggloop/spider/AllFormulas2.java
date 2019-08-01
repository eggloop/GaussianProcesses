package io.github.eggloop.spider;

import io.github.eggloop.expression.arithmetic.Assignment;
import io.github.eggloop.expression.arithmetic.Variable;
import io.github.eggloop.expression.relational.GreaterEqualTo;
import io.github.eggloop.expression.relational.LowerEqualTo;
import io.github.eggloop.stl.syntax.*;
import io.github.eggloop.stl.visitor.BooleanTemporalMonitoring;
import io.github.eggloop.stl.visitor.FormulaPrinter;
import io.github.eggloop.stl.visitor.LogicOperatorToken;
import io.github.eggloop.trajectories.Trajectory;
import io.github.eggloop.trajectories.TrajectoryFactory;
import io.github.eggloop.utils.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.json.simple.parser.ParseException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static io.github.eggloop.utils.CombinatoricsUtility.generateIntervals;

public class AllFormulas2 {

    private AllFormulas2() {
        //utility class
    }


    public static void getTrajectories(String inputLocationOfTrajectories, String outputLocation) throws IOException, ParseException {
        String jsonTrajectories = FileUtils.readFileToString(inputLocationOfTrajectories);
        Trajectory[] trajectories = TrajectoryFactory.fromJSONMultiple(jsonTrajectories);
        double[] times = trajectories[0].getTimes();
        List<double[]> intervals = generateIntervals(times);
        double[] space = IntStream.range(-8, 18).mapToDouble(s -> 0.1 * s).toArray();
        List<double[]> spaceIntervals = generateIntervals(space);
        BooleanTemporalMonitoring[] booleanTemporalMonitoring = Arrays.stream(trajectories).map(BooleanTemporalMonitoring::new).toArray(BooleanTemporalMonitoring[]::new);
        FormulaPrinter formulaPrinter = new FormulaPrinter(new LogicOperatorToken());
        System.out.println("INIT");
//        List<double[]> parameters = getParameters(spaceIntervals, intervals);
//        System.out.println("INIT");
//        List<double[]> parameters2 = getParameters(parameters, intervals);
//        System.out.println("INIT");

        Formula atoml = new Atom(new GreaterEqualTo(new Variable("X"), new Variable("h")));
        Formula atomh = new Atom(new LowerEqualTo(new Variable("X"), new Variable("l")));
        Formula aFinally = new Finally(new Interval(new Variable("a"), new Variable("b")), new Conjunction(atoml, atomh));
        Formula aGlobally = new Globally(new Interval(new Variable("a"), new Variable("b")), new Conjunction(atoml, atomh));
        Formula aGloballyFinally = new Globally(new Interval(new Variable("c"), new Variable("d")), new Finally(new Interval(new Variable("a"), new Variable("b")), new Conjunction(atoml, atomh)));
        Formula aFinallyGlobally = new Finally(new Interval(new Variable("c"), new Variable("d")), new Globally(new Interval(new Variable("a"), new Variable("b")), new Conjunction(atoml, atomh)));

        Function<double[], Assignment> ass2 = value -> {
            Assignment assignment = new Assignment();
            assignment.put("h", value[0]);
            assignment.put("l", value[1]);
            assignment.put("a", value[2]);
            assignment.put("b", value[3]);
            assignment.put("c", value[4]);
            assignment.put("d", value[5]);
            return assignment;
        };

        Function<double[], Assignment> ass = value -> {
            Assignment assignment = new Assignment();
            assignment.put("h", value[0]);
            assignment.put("l", value[1]);
            assignment.put("a", value[2]);
            assignment.put("b", value[3]);
            return assignment;
        };


        Predicate<double[]>[] finallycheck = Arrays.stream(booleanTemporalMonitoring).map(s -> construct(aFinally, s, ass)).toArray(Predicate[]::new);
        Predicate<double[]>[] globallycheck = Arrays.stream(booleanTemporalMonitoring).map(s -> construct(aGlobally, s, ass)).toArray(Predicate[]::new);
        Predicate<double[]>[] finallyGloballycheck = Arrays.stream(booleanTemporalMonitoring).map(s -> construct(aFinallyGlobally, s, ass2)).toArray(Predicate[]::new);
        Predicate<double[]>[] globallyFinallycheck = Arrays.stream(booleanTemporalMonitoring).map(s -> construct(aGloballyFinally, s, ass2)).toArray(Predicate[]::new);

        StringBuilder stringBuilder = new StringBuilder();
        long init = System.currentTimeMillis();
        AtomicInteger counter = new AtomicInteger();
        System.out.println("INIT");
//        ParameterSupplier parameters = new ParameterSupplier(spaceIntervals, intervals);
//        parameters.setConfiguration(1, 1);
//        getStrings(stringBuilder, parameters, formulaPrinter, aFinally, ass, finallycheck, counter);
//        System.out.println(System.currentTimeMillis() - init);
//        System.out.println("COUNTER:" + counter.get());
//        parameters = new ParameterSupplier(spaceIntervals, intervals);
//        parameters.setConfiguration(1, 1);
//        getStrings(stringBuilder, parameters, formulaPrinter, aGlobally, ass, globallycheck, counter);
//        System.out.println(System.currentTimeMillis() - init);
//        System.out.println("COUNTER:" + counter.get());
        ParameterSupplier parameters = new ParameterSupplier(spaceIntervals, intervals);
        parameters.setConfiguration(1, 2);
        parameters.length();
        getStrings(stringBuilder, parameters, formulaPrinter, aGloballyFinally, ass2, globallyFinallycheck, counter);
        System.out.println(System.currentTimeMillis() - init);
        System.out.println("COUNTER:" + counter.get());
        parameters = new ParameterSupplier(spaceIntervals, intervals);
        parameters.setConfiguration(1, 2);
        getStrings(stringBuilder, parameters, formulaPrinter, aFinallyGlobally, ass2, finallyGloballycheck, counter);
        System.out.println(System.currentTimeMillis() - init);
        System.out.println("COUNTER:" + counter.get());
        Path path = Paths.get(outputLocation);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(stringBuilder.toString());
        }


    }

    private static List<double[]> getParameters(List<double[]> spaceIntervals, List<double[]> intervals) {
        List<double[]> params = new ArrayList<>();
        for (double[] interval : intervals) {
            for (double[] spaceInterval : spaceIntervals) {
                params.add(ArrayUtils.addAll(spaceInterval, interval));
            }
        }
        return params;
    }

//    private static List<double[]> getParameters2(List<double[]> spaceIntervals, List<double[]> intervals) {
//        List<double[]> params = new ArrayList<>();
//        for (double[] interval : intervals) {
//            for (double[] interval2 : intervals) {
//                for (double[] spaceInterval : spaceIntervals) {
//                    params.add();
//                }
//            }
//        }
//        return params;
//    }

    private static boolean check(Predicate<double[]>[] eval, double[] param) {
        boolean b = Arrays.stream(eval).parallel().anyMatch(s -> s.test(param));
        boolean c = Arrays.stream(eval).parallel().anyMatch(s -> !s.test(param));
        return b && c;
    }

    private static void getStrings(StringBuilder stringBuilder, ParameterSupplier parameters, FormulaPrinter formulaPrinter, Formula formula, Function<double[], Assignment> ass, Predicate<double[]>[] eval, AtomicInteger counter) {
        Function<double[], String> printer = value -> formula.accept(formulaPrinter).evaluate(ass.apply(value));
        Stream.generate(parameters).limit(parameters.length()).filter(s -> check(eval, s)).peek(s -> counter.incrementAndGet()).forEach(s -> stringBuilder.append(printer.apply(s)).append("\n"));
    }


    private static void getStrings(StringBuilder stringBuilder, List<double[]> spaceIntervals, List<double[]>
            intervals, FormulaPrinter formulaPrinter, Formula formula, Function<double[], Assignment> ass, Predicate<
            double[]>[] eval) {
        Function<double[], String> printer = value -> formula.accept(formulaPrinter).evaluate(ass.apply(value));
        for (double[] interval : intervals) {
            for (double[] spaceInterval : spaceIntervals) {
                double[] params = {spaceInterval[0], spaceInterval[1], interval[0], interval[1]};
                long count = Arrays.stream(eval).parallel().filter(s -> s.test(params)).count();
                if (count > 0 && count < eval.length) {
                    stringBuilder.append(printer.apply(params)).append(" C:").append(count).append("\n");
                }
            }
        }
    }


    public static Predicate<double[]> construct(Formula formula, BooleanTemporalMonitoring monitor, Function<
            double[], Assignment> function) {
        return value -> formula.accept(monitor).evaluate(function.apply(value));
    }

    public static void getTrajectory(String inputLocationOfTrajectories, String outputLocation) throws
            IOException, ParseException {
        String jsonTrajectory = FileUtils.readFileToString(inputLocationOfTrajectories);
        Trajectory trajectory = TrajectoryFactory.fromJSON(jsonTrajectory);
        double[] times = trajectory.getTimes();
//        double[] times = IntStream.range(0, 11).mapToDouble(s -> s).toArray();
        List<double[]> intervals = generateIntervals(times);

        double[] space = IntStream.range(-8, 18).mapToDouble(s -> 0.1 * s).toArray();
        List<double[]> spaceIntervals = generateIntervals(space);

//        List<double[]> intervals = new ArrayList<>();
//        for (int i = 0; i < times.length; i++) {
//            double initTime = times[i];
//            for (int j = i + 2; j < times.length; j++) {
//                intervals.add(new double[]{initTime, times[j]});
//            }
//        }


        BooleanTemporalMonitoring booleanTemporalMonitoring = new BooleanTemporalMonitoring(trajectory);
        FormulaPrinter formulaPrinter = new FormulaPrinter(new LogicOperatorToken());


        Formula atoml = new Atom(new GreaterEqualTo(new Variable("X"), new Variable("h")));
        Formula atomh = new Atom(new LowerEqualTo(new Variable("X"), new Variable("l")));
        Formula aFinally = new Finally(new Interval(new Variable("a"), new Variable("b")), new Conjunction(atoml, atomh));
        Formula aGlobally = new Globally(new Interval(new Variable("a"), new Variable("b")), new Conjunction(atoml, atomh));
        Formula aGloballyFinally = new Globally(new Interval(new Variable("c"), new Variable("d")), new Finally(new Interval(new Variable("a"), new Variable("b")), new Conjunction(atoml, atomh)));
        Formula aFinallyGlobally = new Finally(new Interval(new Variable("c"), new Variable("d")), new Globally(new Interval(new Variable("a"), new Variable("b")), new Conjunction(atoml, atomh)));

        Function<double[], Assignment> ass2 = value -> {
            Assignment assignment = new Assignment();
            assignment.put("h", value[0]);
            assignment.put("l", value[1]);
            assignment.put("a", value[2]);
            assignment.put("b", value[3]);
            assignment.put("c", value[4]);
            assignment.put("d", value[5]);
            return assignment;
        };

        Function<double[], Assignment> ass = value -> {
            Assignment assignment = new Assignment();
            assignment.put("h", value[0]);
            assignment.put("l", value[1]);
            assignment.put("a", value[2]);
            assignment.put("b", value[3]);
            return assignment;
        };


        Predicate<double[]> finallycheck = value -> aFinally.accept(booleanTemporalMonitoring).evaluate(ass.apply(value));
        Predicate<double[]> globallycheck = value -> aGlobally.accept(booleanTemporalMonitoring).evaluate(ass.apply(value));
        Predicate<double[]> finallyGloballycheck = value -> aFinallyGlobally.accept(booleanTemporalMonitoring).evaluate(ass2.apply(value));
        Predicate<double[]> globallyFinallycheck = value -> aGloballyFinally.accept(booleanTemporalMonitoring).evaluate(ass2.apply(value));


        StringBuilder stringBuilder = new StringBuilder();

        double[][] param = getParamsGrid(spaceIntervals, intervals);

        long initA = System.currentTimeMillis();
        double[][] validParam = Arrays.stream(param).parallel().filter(finallycheck).toArray(double[][]::new);
        System.out.println("stream:" + (System.currentTimeMillis() - initA));
        //motorino
        long init = System.currentTimeMillis();
        getString(stringBuilder, spaceIntervals, intervals, formulaPrinter, aFinally, ass, finallycheck);
//        getString(stringBuilder, spaceIntervals, intervals, formulaPrinter, aGlobally, ass, globallycheck);
//        getString2(stringBuilder, spaceIntervals, intervals, formulaPrinter, aFinallyGlobally, ass2, finallyGloballycheck);
//        getString2(stringBuilder, spaceIntervals, intervals, formulaPrinter, aGloballyFinally, ass2, globallyFinallycheck);
        long total = System.currentTimeMillis() - init;
        System.out.println(total);

        Path path = Paths.get(outputLocation);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(stringBuilder.toString());
        }
    }

    private static void getString(StringBuilder stringBuilder, List<double[]> spaceIntervals, List<double[]>
            intervals, FormulaPrinter formulaPrinter, Formula formula, Function<double[], Assignment> ass, Predicate<
            double[]> eval) {
        Function<double[], String> printer = value -> formula.accept(formulaPrinter).evaluate(ass.apply(value));
        for (double[] interval : intervals) {
            for (double[] spaceInterval : spaceIntervals) {
                double[] params = {spaceInterval[0], spaceInterval[1], interval[0], interval[1]};
                boolean test = eval.test(params);
                if (test) {
                    stringBuilder.append(printer.apply(params)).append("\n");
                }
            }
        }
    }


    private static double[][] getParamsGrid(List<double[]> spaceIntervals, List<double[]> intervals) {
        double[][] params = new double[intervals.size() * spaceIntervals.size()][];
        int c = 0;
        for (double[] interval : intervals) {
            for (double[] spaceInterval : spaceIntervals) {
                params[c++] = new double[]{spaceInterval[0], spaceInterval[1], interval[0], interval[1]};
            }
        }
        return params;
    }

    private static void getString2(StringBuilder stringBuilder, List<double[]> spaceIntervals, List<double[]>
            intervals, FormulaPrinter formulaPrinter, Formula formula, Function<double[], Assignment> ass, Predicate<
            double[]> eval) {
        Function<double[], String> printer = value -> formula.accept(formulaPrinter).evaluate(ass.apply(value));
        for (double[] interval : intervals) {
            for (double[] interval2 : intervals) {
                for (double[] spaceInterval : spaceIntervals) {
                    if (interval2[1] + interval[1] < 10) {
                        double[] params = {spaceInterval[0], spaceInterval[1], interval[0], interval[1], interval2[0], interval2[1]};
                        boolean test = eval.test(params);
                        if (test) {
                            stringBuilder.append(printer.apply(params)).append("\n");
                        }
                    }
                }
            }
        }
    }

    private static void getStrings2(StringBuilder stringBuilder, List<double[]> spaceIntervals, List<double[]>
            intervals, FormulaPrinter formulaPrinter, Formula formula, Function<double[], Assignment> ass, Predicate<
            double[]>[] eval) {
        Function<double[], String> printer = value -> formula.accept(formulaPrinter).evaluate(ass.apply(value));
        for (double[] interval : intervals) {
            for (double[] interval2 : intervals) {
                for (double[] spaceInterval : spaceIntervals) {
                    if (interval2[1] + interval[1] < 10) {
                        double[] params = {spaceInterval[0], spaceInterval[1], interval[0], interval[1], interval2[0], interval2[1]};
                        long count = Arrays.stream(eval).parallel().filter(s -> s.test(params)).count();
                        if (count > 0 && count < eval.length) {
                            stringBuilder.append(printer.apply(params)).append(" C:").append(count).append("\n");
                        }
                    }
                }
            }
        }


    }
}