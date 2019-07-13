package io.github.eggloop.trajectories;


public class Trajectory {

    private String[] variables;
    private double[] times;
    private double[][] values;

    public Trajectory(String[] variables, double[] times, double[][] values) {
        this.variables = variables;
        this.times = times;
        this.values = values;
    }

    public String[] getVariables() {
        return variables;
    }

    public double[] getTimes() {
        return times;
    }

    public double[][] getValues() {
        return values;
    }
}