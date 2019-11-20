package io.github.eggloop.trajectories;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Collections;
import java.util.stream.IntStream;

public class TrajectoryFactory {

    private TrajectoryFactory() {
        //do nothing
    }

    public static String toJSON(Trajectory trajectory) {
        JSONObject jsonTrajectory = new JSONObject();
        JSONArray names = new JSONArray();
        Collections.addAll(names, trajectory.getVariables());
        jsonTrajectory.put("variables", names);
        jsonTrajectory.put("time", toJSON(trajectory.getTimes()));
        jsonTrajectory.put("values", toJSON(trajectory.getValues()));
        return jsonTrajectory.toJSONString();
    }

    public static Trajectory fromJSON(String jsonEntry) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject root = (JSONObject) parser.parse(jsonEntry);
        JSONArray variablesJson = (JSONArray) (root.get("variables"));
        String[] variables = (String[]) variablesJson.toArray(new String[0]);
        JSONArray timeJson = (JSONArray) root.get("time");
        double[] time = toDoubles(timeJson);
        double[][] values = toMatrix((JSONArray) root.get("values"));
        return new Trajectory(variables, time, values);
    }

    public static Trajectory[] fromJSONMultiple(String jsonEntry) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject root = (JSONObject) parser.parse(jsonEntry);
        JSONArray traces = (JSONArray) (root.get("traces"));
        String[] tracesString = new String[traces.size()];
        for (int i = 0; i < tracesString.length; i++) {
            tracesString[i] = ((JSONObject) traces.get(i)).toJSONString();
        }
        Trajectory[] trajectories = new Trajectory[traces.size()];
        for (int i = 0; i < tracesString.length; i++) {
            trajectories[i] = fromJSON(tracesString[i]);
        }
        return trajectories;
    }

    public static double[] fromJSONRewards(String jsonEntry) throws ParseException {
        return fromJSONTag(jsonEntry,"rewards");
    }

    public static double[] fromJSONWeights(String jsonEntry) throws ParseException {
        return fromJSONTag(jsonEntry,"weights");
    }

    private static double[] fromJSONTag(String jsonEntry, String tag) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject root = (JSONObject) parser.parse(jsonEntry);
        JSONArray rewards = (JSONArray) (root.get(tag));
        return toDoubles(rewards);
    }


    private static JSONArray toJSON(double[] value) {
        JSONArray array = new JSONArray();
        for (double element : value) {
            array.add(element);
        }
        return array;
    }

    private static JSONArray toJSON(double[][] matrix) {
        JSONArray array = new JSONArray();
        for (double[] row : matrix) {
            array.add(toJSON(row));
        }
        return array;
    }

    private static double[] toDoubles(JSONArray array) {
        return IntStream.range(0, array.size()).mapToDouble(s -> (double) array.get(s)).toArray();
    }

    private static double[][] toMatrix(JSONArray array) {
        double[][] res = new double[array.size()][];
        for (int i = 0; i < res.length; i++) {
            res[i] = toDoubles((JSONArray) array.get(i));
        }
        return res;
    }
}