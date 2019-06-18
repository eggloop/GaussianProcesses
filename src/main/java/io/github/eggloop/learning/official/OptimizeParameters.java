package io.github.eggloop.learning.official;

import io.github.eggloop.expr.Context;
import io.github.eggloop.learning.Learning;
import io.github.eggloop.mitl.MiTL;
import io.github.eggloop.model.Trajectory;
import io.github.eggloop.parsers.MitlFactory;
import io.github.eggloop.utils.files.Utils;
import io.github.eggloop.utils.string.StringUtils;

import java.util.Arrays;
import java.util.function.UnaryOperator;

public class OptimizeParameters {
    private static final UnaryOperator<String> FILE_PATH = Utils.getFilePath(Learning.class);
    private static double[] ds2Times = Utils.readVectorFromFile(FILE_PATH.apply("ST_LIB_data/ev_spike/timexy.txt"));
    private static double[][][] ds2SpatialValues = Utils.readNewMatrixMultiFromFile(2, FILE_PATH.apply("ST_LIB_data/ev_spike/trajxy.txt"));
    private static double[] lables = Utils.readVectorFromFile(FILE_PATH.apply("ST_LIB_data/ev_spike/labelxy.txt"));

    //::phi:: G[Tl_176, Tu_176] ((y >= Theta_3 | z <= Theta_6))  ::score:: 1.90375763520218 ::parameters:: [11.0, 11.0, 48.990792215517644, 37.53885223004586]
    public static void main(String[] args) {
        String[] variables = new String[]{"X", "Y"};
        String[] parameters = new String[]{"Tl_176", "Tu_176", "a"};
        double[] parametersValues = new double[]{0, 40 - 0.4, 0.1};
        String formula = "F[Tl_176, Tu_176] ((Y-X)>a & (F[0, 0.4] (X-Y)>a))\n)";
//        int positiveClassified = check(ds2Times, ds2SpatialValues, variables, parameters, formula, parametersValues, 0);
        int[] missClassified = check2(lables, ds2Times, ds2SpatialValues, variables, parameters, formula, parametersValues, 0);
        System.out.println("TOTAL:" + ds2SpatialValues.length);
//        System.out.println("POSITIVE CLASSIFIED:" + positiveClassified);
        System.out.println("POSITIVE MISSCLASSIFIED COUNTER:" + missClassified[0]);
        System.out.println("NEGATIVE MISSCLASSIFIED COUNTER:" + missClassified[1]);
    }

    private static int check(double[] times, double[][][] trajectories, String[] variables, String[] parameters, String formula, double[] parameterValues, double atTime) {
        int[] b = new int[trajectories.length];
        Context ns = new Context(variables);
        MitlFactory factory = new MitlFactory(ns);
        String text = StringUtils.replace(formula, parameters, parameterValues) + "\n";
        MiTL prop = factory.constructProperty(text);

        for (int i = 0; i < trajectories.length; i++) {
            Trajectory x = new Trajectory(times, ns, trajectories[i]);
            b[i] = prop.evaluate(x, atTime) ? 1 : 0;
        }
        return Arrays.stream(b).sum();
    }

    private static int [] check2(double[] labels, double[] times, double[][][] trajectories, String[] variables, String[] parameters, String formula, double[] parameterValues, double atTime) {
        Context ns = new Context(variables);
        MitlFactory factory = new MitlFactory(ns);
        String text = StringUtils.replace(formula, parameters, parameterValues) + "\n";
        MiTL prop = factory.constructProperty(text);
        int positiveMissClassified = 0;
        int negativeMissClassified = 0;

        for (int i = 0; i < trajectories.length; i++) {
            Trajectory x = new Trajectory(times, ns, trajectories[i]);
            if(prop.evaluate(x,atTime)){
                if(labels[i]<0){
                    positiveMissClassified++;
                }
            } else {
                if(labels[i]>0){
                    negativeMissClassified++;
                }
            }
        }
        return new int[]{positiveMissClassified,negativeMissClassified};
    }
}