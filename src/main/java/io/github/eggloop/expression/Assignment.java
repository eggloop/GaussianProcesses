package io.github.eggloop.expression;

import java.util.Map;

public class Assignment {

    private Map<String, Double> assignment;

    public Assignment(Map<String, Double> assignement) {
        this.assignment = assignement;
    }

    boolean contains(String name) {
        return assignment.containsKey(name);
    }

    double get(String key) {
        return assignment.get(key);
    }

}
