package io.github.eggloop.spider;

import io.github.eggloop.expression.arithmetic.Assignment;
import io.github.eggloop.expression.arithmetic.Variable;
import io.github.eggloop.expression.relational.GreaterEqualTo;
import io.github.eggloop.expression.relational.LowerEqualTo;
import io.github.eggloop.spider.gradient.WeightCalculator;
import io.github.eggloop.stl.syntax.*;
import io.github.eggloop.stl.visitor.BooleanTemporalMonitoring;
import io.github.eggloop.trajectories.Trajectory;
import io.github.eggloop.trajectories.TrajectoryFactory;
import io.github.eggloop.utils.FileUtils;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static io.github.eggloop.utils.CombinatoricsUtility.generateIntervals;

public class Spider {

    private Spider() {
        //utility class
    }

    public static void getTrajectories(String inputLocationOfTrajectories, String outputLocation, int lower, int upper) throws IOException, ParseException {
        String jsonTrajectories = FileUtils.readFileToString(inputLocationOfTrajectories);
        Trajectory[] trajectories = TrajectoryFactory.fromJSONMultiple(jsonTrajectories);
        double[] times = trajectories[0].getTimes();
        List<double[]> intervals = generateIntervals(times);
        double[] space = IntStream.range(lower, upper).mapToDouble(s -> 0.1 * s).toArray();
        List<double[]> spaceIntervals = generateIntervals(space);
        BooleanTemporalMonitoring[] booleanTemporalMonitoring = Arrays.stream(trajectories).map(BooleanTemporalMonitoring::new).toArray(BooleanTemporalMonitoring[]::new);
        // FormulaPrinter formulaPrinter = new FormulaPrinter(new LogicOperatorToken());
        Formula atoml = new Atom(new GreaterEqualTo(new Variable("X"), new Variable("h")));
        Formula atomh = new Atom(new LowerEqualTo(new Variable("X"), new Variable("l")));
        Formula aFinally = new Finally(new Interval(new Variable("a"), new Variable("b")), new Conjunction(atoml, atomh));
        Formula aGlobally = new Globally(new Interval(new Variable("a"), new Variable("b")), new Conjunction(atoml, atomh));

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
        long init = System.currentTimeMillis();
        AtomicInteger counter = new AtomicInteger();
        System.out.println("START");
        ParameterSupplier parameters = new ParameterSupplier(spaceIntervals, intervals);
        parameters.setConfiguration(1, 1);
        List<double[]> eventuallyParameters = getStrings(parameters, finallycheck, counter);
        parameters = new ParameterSupplier(spaceIntervals, intervals);
        parameters.setConfiguration(1, 1);
        List<double[]> globallyParameters = getStrings(parameters, globallycheck, counter);
        System.out.println("Formulas Number:" + counter.get());
        Bean storage = new Bean(eventuallyParameters, globallyParameters);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(outputLocation)))) {
            oos.writeObject(storage);
        }
        double endTimeSec = (System.currentTimeMillis() - init) / 1000.0;
        System.out.println("END (time:" + endTimeSec + " sec)");
    }


    public static void evaluateSingleTrajectory(String beanFilePath) throws IOException, ClassNotFoundException, ParseException {
        try (ObjectInputStream iis = new ObjectInputStream(new FileInputStream(beanFilePath))) {
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            Bean bean = (Bean) iis.readObject();
            while (true) {
                String inputLocationOfTrajectory = stdIn.readLine();
                if (inputLocationOfTrajectory != null && !"EXIT".equals(inputLocationOfTrajectory)) {
                    //String jsonTrajectories = FileUtils.readFileToString(inputLocationOfTrajectory);
                    String jsonTrajectories = inputLocationOfTrajectory;
                    Trajectory trajectory = TrajectoryFactory.fromJSON(jsonTrajectories);
                    Formula atoml = new Atom(new GreaterEqualTo(new Variable("X"), new Variable("h")));
                    Formula atomh = new Atom(new LowerEqualTo(new Variable("X"), new Variable("l")));
                    Formula aFinally = new Finally(new Interval(new Variable("a"), new Variable("b")), new Conjunction(atoml, atomh));
                    Formula aGlobally = new Globally(new Interval(new Variable("a"), new Variable("b")), new Conjunction(atoml, atomh));

                    Function<double[], Assignment> ass = value -> {
                        Assignment assignment = new Assignment();
                        assignment.put("h", value[0]);
                        assignment.put("l", value[1]);
                        assignment.put("a", value[2]);
                        assignment.put("b", value[3]);
                        return assignment;
                    };

                    BooleanTemporalMonitoring booleanTemporalMonitoring = new BooleanTemporalMonitoring(trajectory);
                    Predicate<double[]> finallyCheck = construct(aFinally, booleanTemporalMonitoring, ass);
                    Predicate<double[]> globallyCheck = construct(aGlobally, booleanTemporalMonitoring, ass);
                    bean.getEventually().forEach(s -> System.out.print(finallyCheck.test(s) ? "1 " : "0 "));
                    bean.getGlobally().forEach(s -> System.out.print(globallyCheck.test(s) ? "1 " : "0 "));
                    System.out.println("\n");
                }
                if ("EXIT".equals(inputLocationOfTrajectory)) {
                    return;
                }
            }
        }
    }


    public static void evaluateGradient(String beanFilePath, String trajectoryFilePath, String rewardsFilePath, String outputFilePath, double alpha, double lambda) throws IOException, ClassNotFoundException, ParseException {
        try (ObjectInputStream iis = new ObjectInputStream(new FileInputStream(beanFilePath))) {
            Bean bean = (Bean) iis.readObject();
            Bean fake = new Bean(bean.getEventually().subList(0,20),bean.getGlobally().subList(0,20));
            String jsonTrajectories = FileUtils.readFileToString(trajectoryFilePath);
            String jsonRewards = FileUtils.readFileToString(rewardsFilePath);
            Trajectory[] trajectories = TrajectoryFactory.fromJSONMultiple(jsonTrajectories);
            double[] rewards = TrajectoryFactory.fromJSONRewards(jsonRewards);
           // Arrays.fill(rewards, 1);
            WeightCalculator weightCalculator = new WeightCalculator(fake, alpha, lambda);
            double[] weight = weightCalculator.updateAll(rewards, trajectories);
            Path path = Paths.get(outputFilePath);
            try (BufferedWriter writer = Files.newBufferedWriter(path)) {
                writer.write(Arrays.toString(weight));
            }
        }
    }

    private static boolean check(Predicate<double[]>[] eval, double[] param) {
        boolean b = Arrays.stream(eval).parallel().anyMatch(s -> s.test(param));
        boolean c = Arrays.stream(eval).parallel().anyMatch(s -> !s.test(param));
        return b && c;
    }

    private static List<double[]> getStrings(ParameterSupplier parameters, Predicate<double[]>[] eval, AtomicInteger counter) {
        // Function<double[], String> printer = value -> formula.accept(formulaPrinter).evaluate(ass.apply(value));
        return Stream.generate(parameters).limit(parameters.length()).filter(s -> check(eval, s)).peek(s -> counter.incrementAndGet()).collect(Collectors.toList());
    }

    private static Predicate<double[]> construct(Formula formula, BooleanTemporalMonitoring monitor, Function<
            double[], Assignment> assignment) {
        return value -> formula.accept(monitor).evaluate(assignment.apply(value));
    }
}