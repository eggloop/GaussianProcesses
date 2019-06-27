package io.github.eggloop.expression.arithmetic;

import java.util.Map;

public class Assignment {

    private Map<String, Double> assignment;

    public Assignment(Map<String, Double> assignment) {
        this.assignment = assignment;
    }

    boolean contains(String name) {
        return assignment.containsKey(name);
    }

    double get(String key) {
        return assignment.get(key);
    }

}
