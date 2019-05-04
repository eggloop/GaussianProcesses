package io.github.eggloop.learning;

import io.github.eggloop.model.Trajectories;
import io.github.eggloop.model.TrajectoriesFactory;
import io.github.eggloop.simhya.simhya.matlab.genetic.Formula;
import io.github.eggloop.simhya.simhya.matlab.genetic.FormulaPopulation;
import io.github.eggloop.simhya.simhya.matlab.genetic.GeneticOptions;
import io.github.eggloop.utils.files.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExampleLearning2 {
    private static final Logger LOGGER = LoggerFactory.get();
    private static final UnaryOperator<String> FILE_PATH = Utils.getFilePath(Learning.class);
    private static final BiFunction<double[], double[], Double> DISCRIMINATION_FUNCTION = (x, y) -> (x[0] - y[0]) / (Math.abs(x[1] + y[1]));
    private static Learning2 learning;

    public static void main(String[] args) throws InterruptedException {
        LOGGER.setLevel(Level.FINE);
        learning = new Learning2(LOGGER, DISCRIMINATION_FUNCTION);
        Random random = new Random();
        List<Trajectories> set = TrajectoriesFactory.fromFiles(new String[]{"x", "y"}, FILE_PATH.apply("data/navalTimes"), FILE_PATH.apply("data/navalLabels"), FILE_PATH.apply("data/navalData"))
                .split(random, 0.8);
        Trajectories trainingSet = set.get(0);
        Trajectories testSet = set.get(1);
        learn(random, trainingSet, testSet, LOGGER);
//        learnCV(new Random(), LOGGER);
    }


    private static void learn(Random random, Trajectories trainingSet, Trajectories testSet, Logger logger) throws InterruptedException {
        double[][] performances = core(random, trainingSet, testSet, logger);
        System.out.println("Classified as Negative Trajectories [percentage, variance] = " + Arrays.toString(performances[0]));
        System.out.println("Classified as Positive Trajectories [percentage, variance] = " + Arrays.toString(performances[1]));
    }

    private static double[][] core(Random random, Trajectories trainingSet, Trajectories testSet, Logger logger) throws InterruptedException {
        int NE = 100;   // number of formulae in the initial population
        FormulaPopulation pop = new FormulaPopulation(NE);
        String[] variables = new String[]{"x", "y"};

        //addVariableInformation to population, bounds of the interval parameters
        double atTime = 0;
        double[] lower = new double[]{0, 0};
        double[] upper = new double[]{80, 45};

        logger.log(Level.INFO, "Formula Variables:" + Arrays.toString(variables));
        logger.log(Level.INFO, "Formula Variables Lower Bounds:" + Arrays.toString(lower));
        logger.log(Level.INFO, "Formula Variables Upper Bounds:" + Arrays.toString(upper));

        for (int i = 0; i < variables.length; i++) {
            pop.addVariable(variables[i], lower[i], upper[i]);
        }

        //set genetic options
//        GeneticOptions.init__globallyeventually_weight = 0.10;
//        GeneticOptions.init__eventuallyglobally_weight = 0.10;
//        GeneticOptions.init__globally_weight = 0.01;
//        GeneticOptions.init__eventually_weight = 0.01;
//        GeneticOptions.init__not_weight = 0.1;
//        GeneticOptions.init__until_weight = 0.5;
//        GeneticOptions.mutate__one_node = false;
        //GeneticOptions.setInit__fixed_number_of_atoms(4);
        GeneticOptions.setInit__prob_of_true_atom(0);
        GeneticOptions.setMin_time_bound(0);
        GeneticOptions.setMax_time_bound(300);

        //adding initial Formulae (atomic + G +F + U )
        pop.addGeneticInitFormula(pop.getVariableNumber());

        //adding random formulae
        double maxNumRand = 100 - pop.getPopulationSize();
        for (int i = 0; i < maxNumRand; i++) {
            pop.addRandomInitFormula();
        }
        GeneticPopulation generation = null;
        int NG = 10;
        logger.log(Level.INFO, "NUMBER OF GENERATIONS:" + NG);
        logger.log(Level.INFO, "GENETIC ALGORITHM - START");
        for (int k = 0; k < NG; k++) {
            logger.log(Level.INFO, "GENERATION #:" + k);
            logger.log(Level.INFO, "> OPTIMIZING POPULATION PARAMETER");
            generation = learning.optimizeGenerationParameters(pop, variables, trainingSet, testSet, atTime);
            generation.sort();
            logger.log(Level.INFO, "-----------------------------");
            logger.log(Level.INFO, "FORMULA GENERATION");
            logger.log(Level.INFO, generation.toString());
            logger.log(Level.INFO, "-----------------------------");
            logger.log(Level.INFO, "> GETTING THE HALF BEST INDIVIDUALS ");
            GeneticPopulation bestHalf = generation.getBestHalf();
            logger.log(Level.INFO, "> APPLYING GENETIC OPERATIONS: OFFSPRING FORMULA GENERATION");
            bestHalf.geneticOperations(random, pop);
            logger.log(Level.INFO, "> OPTIMIZING OFFSPRING FORMULA PARAMETER");
            GeneticPopulation betsHalfGeneration = learning.optimizeGenerationParameters(pop, variables,trainingSet,testSet, atTime);
            betsHalfGeneration.sort();
            betsHalfGeneration.getBestHalf();
            logger.log(Level.INFO, "> UNION OFFSPRING - PARENTS");
            generation.addall(betsHalfGeneration);
        }
        generation.sort();
        logger.log(Level.INFO, "GENETIC ALGORITHM - END");
        logger.log(Level.INFO, "-----------------------------");
        logger.log(Level.INFO, "LAST FORMULA GENERATION");
        logger.log(Level.INFO, generation.toString());
        logger.log(Level.INFO, "-----------------------------");

        Formula bestFormula = generation.getBestFromula();
        logger.log(Level.INFO, "REFINING BEST PARAMETER FORMULA");
        //double[] bestFormulaParameters = ComputeAverage.averageMultiTrajectory(100, DISCRIMINATION_FUNCTION, variables, ds2Times, positiveTrainingSet, negativeTrainingSet, bestFormula, pop, new double[]{GeneticOptions.min_time_bound, GeneticOptions.max_time_bound}, atTime);
        double[] bestFormulaParameters = generation.getBestFormulaParameters();
        System.out.println("__________________________________________________");
        System.out.println("__________________BEST FORMULA____________________");
        System.out.println("__________________________________________________");
        System.out.println(bestFormula.toString());
        System.out.println("Best Formula Parameters:" + Arrays.toString(bestFormula.getParameters()) + ":::::" + Arrays.toString(bestFormulaParameters));
        //    double[] negativeSetPerformance = smcMultiTrajectories(ds2Times, negativeTestSet, variables, bestFormula, bestFormulaParameters, atTime);
        //   double[] positiveSetPerformance = smcMultiTrajectories(ds2Times, positiveTestSet, variables, bestFormula, bestFormulaParameters, atTime);
        //   return new double[][]{negativeSetPerformance, positiveSetPerformance};
        return null;
    }
}