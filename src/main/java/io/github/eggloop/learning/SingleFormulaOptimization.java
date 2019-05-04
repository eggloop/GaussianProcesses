package io.github.eggloop.learning;

import io.github.eggloop.simhya.simhya.matlab.genetic.Formula;
import io.github.eggloop.simhya.simhya.matlab.genetic.FormulaPopulation;
import io.github.eggloop.utils.data.TrajectoryMultiReconstruction;
import io.github.eggloop.utils.files.Utils;

import java.util.Arrays;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.UnaryOperator;


public class SingleFormulaOptimization {
    private static final UnaryOperator<String> FILE_PATH = Utils.getFilePath(Learning.class);
    private static final BiFunction<double[], double[], Double> DISCRIMINATION_FUNCTION = (x, y) -> (x[0] - y[0]) / (Math.abs(x[1] + y[1]));

    public static void main(String[] args) throws InterruptedException {
        Random ran = new Random();
        //import database
        double[] ds2Labels = Utils.readVectorFromFile(FILE_PATH.apply("data/navalLabels"));
        double[] ds2Times = Utils.readVectorFromFile(FILE_PATH.apply("data/navalTimes"));
        double[][][] ds2SpatialValues = Utils.readMatrixMultiFromFile(ds2Times.length, FILE_PATH.apply("data/navalData"));
        TrajectoryMultiReconstruction data = new TrajectoryMultiReconstruction(ds2Times, ds2SpatialValues, ds2Labels, 0.8, ran);
        data.split();
        double[][][] normal_model = data.getPositiveTrainingSet();
        double[][][] ineffective_model = data.getNegativeTrainingSet();
        double[][][] normal_model_test = data.getPoistiveTestSet();
        double[][][] ineffective_model_test = data.getNegativeTestSet();

        FormulaPopulation pop = new FormulaPopulation(1);
        String[] variables = new String[]{"x", "y"};
        //addVariableInformation to population
        double[] lower = new double[]{0, 0};
        double[] upper = new double[]{80, 45};
        for (int i = 0; i < variables.length; i++) {
            pop.addVariable(variables[i], lower[i], upper[i]);
        }

        String[] pars = new String[]{"Tl_1", "Tu_1", "Theta_1", "Theta_2"};
        double[] vv = new double[]{0, 200, 20, 30};
        //String FF = "P=?[ ({flow1 <= Theta_1}) U[Tl_1,Tu_1] ({flow <= Theta_2}) ]";

        String FF = "P=?[({y >= Theta_1}) U[Tl_1, Tu_1] ({x <= Theta_2})]";
        Formula formula = pop.loadFormula(FF, pars, vv);
        double[] bestFormulaParameters = ComputeAverage.averageMultiTrajectory(200, DISCRIMINATION_FUNCTION, variables, ds2Times, normal_model, ineffective_model, formula, pop, new double[]{0, 300}, 0);
        System.out.println("__________________________________________________");
        System.out.println("bestFormula:" + formula.toString());
        System.out.println("bestParam:" + Arrays.toString(formula.getParameters()) + ":::::" + Arrays.toString(bestFormulaParameters));
        System.out.println("ineffectiveB= " + Arrays.toString(ComputeAverage.smcMultiTrajectories(ds2Times, ineffective_model_test, variables, formula, bestFormulaParameters, 0)));
        System.out.println("normalB= " + Arrays.toString(ComputeAverage.smcMultiTrajectories(ds2Times, normal_model_test, variables, formula, bestFormulaParameters, 0)));
    }
}

