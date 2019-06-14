package io.github.eggloop.learning;

import io.github.eggloop.model.BooleanVerdict;
import io.github.eggloop.model.Trajectories;
import io.github.eggloop.simhya.simhya.matlab.genetic.Formula;
import io.github.eggloop.simhya.simhya.matlab.genetic.FormulaPopulation;
import io.github.eggloop.simhya.simhya.matlab.genetic.GeneticOptions;
import io.github.eggloop.utils.string.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Learning2 {
    private final Logger logger;
    private final BiFunction<double[], double[], Double> function;
    private final HashMap<String, double[]> bestParameters = new HashMap<>();
    private final HashMap<String, Double> bestScore = new HashMap<>();

    public Learning2(Logger logger, BiFunction<double[], double[], Double> function) {
        this.logger = logger;
        this.function = function;
    }

    public GeneticPopulation optimizeGenerationParameters(FormulaPopulation pop, String[] variables, Trajectories trainingSet, Trajectories testSet, double atTime) throws InterruptedException {
        List<Formula> rankFormulae = new ArrayList<>();
        List<double[]> rankParameters = new ArrayList<>();
        List<Double> rankScore = new ArrayList<>();
        for (int i = 0; i < pop.getPopulationSize(); i++) {
//            System.out.println(i+"/"+pop.getPopulationSize());
            Formula formula = pop.getFormula(i);
            //gpucb
            logger.log(Level.FINE, "OPTIMIZE PARAMETER OF FORMULA " + formula.toString());
            String keyFormula = StringUtils.formulaHash(formula.toString(), formula.getParameters());
            double[] bestParamterFormula;
            if (!bestParameters.containsKey(keyFormula)) {
                bestParamterFormula = ComputeAverage2.averageMultiTrajectory(100, function, variables, trainingSet, formula, pop, new double[]{GeneticOptions.min_time_bound, GeneticOptions.max_time_bound}, atTime);
                bestParameters.put(keyFormula, bestParamterFormula);
            } else {
                bestParamterFormula = bestParameters.get(keyFormula);
            }
            logger.log(Level.FINE, "BEST PARAMS: " + Arrays.toString(bestParamterFormula));
            logger.log(Level.FINE, " ");

            try {
                double score;
                if (!bestScore.containsKey(keyFormula)) {
//                    score = getScore(formula, pop, variables, ds2Times, normal_model, ineffective_model, normal_model_test, ineffective_model_test, atTime);
                    score = getScore2(formula, variables, testSet, bestParamterFormula, atTime);
                    if (Double.isNaN(score)) {
                        score = Double.NEGATIVE_INFINITY;
                    }
                    bestScore.put(keyFormula, score);
                    if (score < 0) {
                        score = -score;
                        formula.negate();
                        bestScore.put(StringUtils.formulaHash(formula.toString(), formula.getParameters()), score);
                    }

                } else {
                    score = bestScore.get(keyFormula);
                }
                rankScore.add(score);
            } catch (Exception ex) {
                logger.log(Level.SEVERE, ex.getMessage(), ex);
                rankScore.add(Double.NEGATIVE_INFINITY);
            } finally {
                rankFormulae.add(formula);
                rankParameters.add(bestParamterFormula);
            }
        }
        return new GeneticPopulation(rankFormulae, rankParameters, rankScore);
    }


    private double getScore(Formula formula, FormulaPopulation pop, String[] variables, double[] ds2Times, double[][][] normal_model, double[][][] ineffective_model, double[][][] normal_model_test, double[][][] ineffective_model_test, double atTime) throws InterruptedException {
        double[] parameters = ComputeAverage.averageMultiTrajectory(30, function, variables, ds2Times, normal_model, ineffective_model, formula, pop, new double[]{GeneticOptions.min_time_bound, GeneticOptions.max_time_bound}, atTime);
        //computeation of G
        double[] p1 = ComputeAverage.computeAverageRobustnessMultiTrajectory(ds2Times, normal_model_test, variables, formula, parameters, atTime);
        double[] p2 = ComputeAverage.computeAverageRobustnessMultiTrajectory(ds2Times, ineffective_model_test, variables, formula, parameters, atTime);
        return function.apply(p1, p2);
    }

    private double getScore2(Formula formula, String[] variables, Trajectories testSet, double[] bestFormulaParameters, double atTime) {
        BooleanVerdict verdict = ComputeAverage2.smcMultiTrajectories(testSet, variables, formula, bestFormulaParameters, atTime);
        return verdict.getStatsPositive()[0] - verdict.getStatsNegative()[0];
    }
}
