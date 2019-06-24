package io.github.eggloop.trajectories;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Collections;

public class TrajectoryFactory {

    public static String toJSON(Trajectory trajectory) {
        JSONObject jsonTrajectory = new JSONObject();
        JSONArray names = new JSONArray();
        Collections.addAll(names, trajectory.getVariables());
        jsonTrajectory.put("variables", names);
        jsonTrajectory.put("time", toJSON(trajectory.getTime()));
        jsonTrajectory.put("values", toJSON(trajectory.getValues()));
        return jsonTrajectory.toJSONString();
    }

    public Trajectory fromJson(String jsonEntry) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject root = (JSONObject) parser.parse(jsonEntry);
        String[] variables = (String[]) root.get("variables");
        double[] time = (double[]) root.get("time");
        double[][] values = (double[][]) root.get("values");
        return new Trajectory(variables, time, values);

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

}
