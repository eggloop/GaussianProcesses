package io.github.eggloop.learning;

import io.github.eggloop.expr.Context;
import io.github.eggloop.mitl.MiTL;
import io.github.eggloop.model.Trajectory;
import io.github.eggloop.parsers.MitlFactory;
import io.github.eggloop.utils.files.Utils;
import io.github.eggloop.utils.string.StringUtils;

import java.util.Arrays;
import java.util.function.UnaryOperator;

public class CheckFormula {
    private static final UnaryOperator<String> FILE_PATH = Utils.getFilePath(Learning.class);
    private static double[] ds2Times = Utils.readVectorFromFile(FILE_PATH.apply("temporal/synthTime_12.txt"));
    private static double[][][] ds2SpatialValues = Utils.readMatrixMultiFromFile(ds2Times.length, FILE_PATH.apply("temporal/synthData_12.txt"));

    //::phi:: G[Tl_176, Tu_176] ((y >= Theta_3 | z <= Theta_6))  ::score:: 1.90375763520218 ::parameters:: [11.0, 11.0, 48.990792215517644, 37.53885223004586]
    public static void main(String[] args) {

        String[] variables = new String[]{"y", "z"};
        String[] parameters = new String[]{"Tl_176", "Tu_176", "Theta_3", "Theta_6"};
        double[] parametersValues = new double[]{11.0, 11.0, 48.990792215517644, 37.53885223004586};
        String formula = "G[Tl_176, Tu_176] ((y >= Theta_3 | z <= Theta_6))";
        int positiveClassified = check(ds2Times, ds2SpatialValues, variables, parameters, formula, parametersValues, 0);
        System.out.println("TOTAL:" + ds2SpatialValues.length);
        System.out.println("POSITIVE CLASSIFIED:" + positiveClassified);
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
}