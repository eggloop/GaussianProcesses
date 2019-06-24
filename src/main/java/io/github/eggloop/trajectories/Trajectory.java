package io.github.eggloop.trajectories;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Collections;

public class Trajectory {

    private String[] variables;
    private double[] time;
    private double[][] values;

    public Trajectory(String[] names, double[] time, double[][] values) {
        this.variables = names;
        this.time = time;
        this.values = values;
    }

    public String[] getVariables() {
        return variables;
    }

    public double[] getTime() {
        return time;
    }

    public double[][] getValues() {
        return values;
    }

    public String toJSON() {
        JSONObject trajectory = new JSONObject();
        JSONArray names = new JSONArray();
        Collections.addAll(names, variables);
        trajectory.put("variables", names);
        trajectory.put("time", toJSON(time));
        trajectory.put("values", toJSON(values));
        return trajectory.toJSONString();
    }

    private JSONArray toJSON(double[] value) {
        JSONArray array = new JSONArray();
        for (double element : value) {
            array.add(element);
        }
        return array;
    }

    private JSONArray toJSON(double[][] matrix) {
        JSONArray array = new JSONArray();
        for (double[] row : matrix) {
            array.add(toJSON(row));
        }
        return array;
    }
}