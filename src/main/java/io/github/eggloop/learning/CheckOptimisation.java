package io.github.eggloop.learning;

import io.github.eggloop.simhya.simhya.matlab.genetic.FormulaPopulation;
import io.github.eggloop.simhya.simhya.matlab.genetic.GeneticOptions;
import io.github.eggloop.utils.data.TrajectoryMultiReconstruction;
import io.github.eggloop.utils.files.Utils;

import java.util.Arrays;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.UnaryOperator;
import java.util.logging.Logger;

import static io.github.eggloop.learning.ComputeAverageNew.smcMultiTrajectories;

public class CheckOptimisation {
    private static final Logger LOGGER = LoggerFactory.get();
    private static final UnaryOperator<String> FILE_PATH = Utils.getFilePath(Learning.class);
    private static final BiFunction<double[], double[], Double> DISCRIMINATION_FUNCTION = (x, y) -> (x[0] - y[0]) / (Math.abs(x[1] + y[1]));
    private static double[] ds2Labels = Utils.readVectorFromFile(FILE_PATH.apply("data/navalLabels"));
    private static double[] ds2Times = Utils.readVectorFromFile(FILE_PATH.apply("data/navalTimes"));
    private static double[][][] ds2SpatialValues = Utils.readMatrixMultiFromFile(ds2Times.length, FILE_PATH.apply("data/navalData"));
    private static Learning learning;

    //::phi:: G[Tl_176, Tu_176] ((y >= Theta_3 | z <= Theta_6))  ::score:: 1.90375763520218 ::parameters:: [11.0, 11.0, 48.990792215517644, 37.53885223004586]
    public static void main(String[] args) throws InterruptedException {
        TrajectoryMultiReconstruction data = new TrajectoryMultiReconstruction(ds2Times, ds2SpatialValues, ds2Labels, 0.8, new Random(2));
        data.split();
        double[][][] positiveTrainingSet = data.getPositiveTrainingSet();
        double[][][] negativeTrainingSet = data.getNegativeTrainingSet();
        double[][][] positiveTestSet = data.getPoistiveTestSet();
        double[][][] negativeTestSet = data.getNegativeTestSet();
        double[] ds2Times = data.getTimes();
        int NE = 50;   // number of formulae in the initial population
        FormulaPopulation pop = new FormulaPopulation(NE);
        String[] variables = new String[]{"x", "y"};

        //addVariableInformation to population, bounds of the interval parameters
        double atTime = 0;
        double[] lower = new double[]{0, 0};
        double[] upper = new double[]{80, 45};

        for (int i = 0; i < variables.length; i++) {
            pop.addVariable(variables[i], lower[i], upper[i]);
        }
        pop.addGeneticInitFormula(pop.getVariableNumber());


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

        double[] bestFormulaParameters = ComputeAverageNew.averageMultiTrajectory(50, DISCRIMINATION_FUNCTION, variables, ds2Times, positiveTrainingSet, negativeTrainingSet, pop.getFormula(0), pop, new double[]{GeneticOptions.min_time_bound, GeneticOptions.max_time_bound}, atTime);
        System.out.println(Arrays.toString(bestFormulaParameters));
        double[] negativeSetPerformance = smcMultiTrajectories(ds2Times, negativeTestSet, variables, pop.getFormula(0), bestFormulaParameters, atTime);
        double[] positiveSetPerformance = smcMultiTrajectories(ds2Times, positiveTestSet, variables, pop.getFormula(0), bestFormulaParameters, atTime);
        System.out.println(Arrays.toString(negativeSetPerformance));
        System.out.println(Arrays.toString(positiveSetPerformance));
    }
}