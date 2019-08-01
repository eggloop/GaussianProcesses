package io.github.eggloop.expression.arithmetic;

import java.util.HashMap;
import java.util.Map;

public class Assignment {

    private Map<String, Double> assignment;

    public Assignment() {
        this.assignment = new HashMap<>();
    }

    public double get(String key) {
        return assignment.get(key);
    }

    public boolean contains(String name) {
        return assignment.containsKey(name);
    }

    public void put(String key, double value) {
        assignment.put(key, value);
    }
}
