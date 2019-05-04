package io.github.eggloop.mitl;


import io.github.eggloop.model.Trajectory;

public abstract class MiTL {

    abstract public boolean evaluate(Trajectory x, double t);

    abstract public double evaluateValue(Trajectory x, double t);

    int timeIndexAfter(final double[] times, final double t) {
        for (int i = 0; i < times.length; i++)
            if (times[i] >= t)
                return i;
        return times.length - 1;
    }

    int timeIndexUntil(final double[] times, final double t) {
        for (int i = 0; i < times.length; i++)
            if (times[i] > t)
                return i - 1;
            else if (times[i] == t)
                return i;
        return times.length - 1;
    }

    public abstract MiTL clone();
}
