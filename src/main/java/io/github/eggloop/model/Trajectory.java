package io.github.eggloop.model;



import io.github.eggloop.expr.Context;

import java.util.Arrays;

public final class Trajectory {

    final private double[] times;
    final private Context context;
    final private double[][] values;

    public Trajectory(double[] times, Context context, double[][] values) {
        this.times = times;
        this.context = context;
        this.values = values;
    }

    public Context getContext() {
        return context;
    }

    public final double[] getTimes() {
        return times;
    }

    public double[][] getValues() {
        return values;
    }

    public double[] getValues(int index) {
        return values[index];
    }

    public int getNumberOfVariables(){
        return context.getVariables().length;
    }

    @Override
    public String toString() {
        final StringBuilder bf = new StringBuilder();
        bf.append("times : ");
        bf.append(Arrays.toString(times));
        bf.append("\n");
        for (int i = 0; i < getNumberOfVariables(); i++) {
            bf.append(context.getVariables()[i].getName());
            bf.append(": ");
            bf.append(Arrays.toString(values[i]));
            bf.append("\n");
        }
        return bf.toString();
    }

    public String toCSV() {
        final StringBuffer bf = new StringBuffer();
        bf.append("# Time");
        for (int v = 0; v < getNumberOfVariables(); v++) {
            bf.append(",\t");
            bf.append(context.getVariables()[v].getName());
        }
        bf.append("\n");
        final int n = times.length;
        for (int i = 0; i < n; i++) {
            bf.append(times[i]);
            for (int v = 0; v < getNumberOfVariables(); v++) {
                bf.append(",\t");
                bf.append(values[v][i]);
            }
            bf.append("\n");
        }
        return bf.toString();
    }

}
