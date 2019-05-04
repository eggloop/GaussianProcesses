package io.github.eggloop.model;

import io.github.eggloop.expr.Context;
import io.github.eggloop.utils.files.Utils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

public class TrajectoriesFactory {

    private static final UnaryOperator<String> FILE_PATH = Utils.getFilePath(TrajectoriesFactory.class);

    public static Trajectories fromJSON(String filePath) throws IOException {
        URI fileURI = new File(filePath).toURI();
        JSONTokener tokener = new JSONTokener(fileURI.toURL().openStream());
        JSONObject root = new JSONObject(tokener);
        JSONArray positive = (JSONArray) root.get("positive");
        JSONArray negative = (JSONArray) root.get("negative");
        double[] time = toDouble((JSONArray) root.get("time"));
        String[] variable = toString((JSONArray) root.get("variable"));
        List<Trajectory> positiveTrajectories = new ArrayList<>();
        for (int i = 0; i < positive.length(); i++) {
            double[][] values = toMatrix((JSONArray) positive.get(i));
            positiveTrajectories.add(new Trajectory(time, new Context(variable), values));
        }
        List<Trajectory> negativeTrajectories = new ArrayList<>();
        for (int i = 0; i < negative.length(); i++) {
            double[][] values = toMatrix((JSONArray) negative.get(i));
            negativeTrajectories.add(new Trajectory(time, new Context(variable), values));
        }
        return new Trajectories(positiveTrajectories, negativeTrajectories);
    }

    public static Trajectories fromFiles(String[] variableNames, String timesPath, String labelsPath, String valuesPath) {
        double[] labels = Utils.readVectorFromFile(labelsPath);
        double[] times = Utils.readVectorFromFile(timesPath);
        double[][][] values = Utils.readMatrixMultiFromFile(times.length, valuesPath);
        List<Trajectory> positive = new ArrayList();
        List<Trajectory> negative = new ArrayList();
        for (int i = 0; i < labels.length; i++) {
            Trajectory trajectory = new Trajectory(times, new Context(variableNames), values[i]);
            if (labels[i] == 1) {
                positive.add(trajectory);
            } else {
                negative.add(trajectory);
            }
        }
        return new Trajectories(positive,negative);


    }


    private static double[][] toMatrix(JSONArray objects) {
        double[][] res = new double[objects.length()][];
        for (int i = 0; i < objects.length(); i++) {
            res[i] = toDouble((JSONArray) objects.get(i));
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        Trajectories trajectories = fromJSON(FILE_PATH.apply("example.json"));
        System.out.println();
        //writeJsonSimpleDemo(FILE_PATH.apply("example.json"));

    }

    private static double[] toDouble(JSONArray vector) {
        double[] res = new double[vector.length()];
        for (int i = 0; i < res.length; i++) {
            res[i] = vector.getDouble(i);
        }
        return res;
    }

    private static String[] toString(JSONArray vector) {
        String[] res = new String[vector.length()];
        for (int i = 0; i < res.length; i++) {
            res[i] = vector.getString(i);
        }
        return res;
    }


}
