package io.github.eggloop.model;

import io.github.eggloop.mitl.MiTL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Trajectories {
    private List<Trajectory> positive;
    private List<Trajectory> negative;

    public Trajectories(List<Trajectory> positive, List<Trajectory> negative) {
        this.positive = positive;
        this.negative = negative;
    }

    public List<Trajectories> split(Random random, double percentage) {
        int positiveTh = (int) Math.floor(positive.size() * percentage);
        int negativeTh = (int) Math.floor(negative.size() * percentage);
        List<Trajectories> res = new ArrayList<>();
        Collections.shuffle(positive, random);
        Collections.shuffle(negative, random);
        res.add(new Trajectories(positive.subList(0, positiveTh), negative.subList(0, negativeTh)));
        res.add(new Trajectories(positive.subList(positiveTh, positive.size()), negative.subList(negativeTh, negative.size())));
        return res;
    }

    public DoubleVerdict doubleVerdict(MiTL formula, double time) {
        double[] pos = new double[positive.size()];
        double[] neg = new double[negative.size()];
        for (int i = 0; i < pos.length; i++) {
            pos[i] = formula.evaluateValue(positive.get(i), time);
        }
        for (int i = 0; i < neg.length; i++) {
            neg[i] = formula.evaluateValue(negative.get(i), time);
        }
        return new DoubleVerdict(pos, neg);
    }

    public BooleanVerdict booleanVerdict(MiTL formula, double time) {
        boolean[] pos = new boolean[positive.size()];
        boolean[] neg = new boolean[negative.size()];
        for (int i = 0; i < pos.length; i++) {
            pos[i] = formula.evaluate(positive.get(i), time);
        }
        for (int i = 0; i < neg.length; i++) {
            neg[i] = formula.evaluate(negative.get(i), time);
        }
        return new BooleanVerdict(pos, neg);
    }
}
