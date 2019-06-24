package io.github.eggloop.trajectories;


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


}