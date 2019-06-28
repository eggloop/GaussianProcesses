package io.github.eggloop.expression.arithmetic;

import java.util.HashMap;
import java.util.Map;

public class Assignment<T> {

    private Map<String, T> assignment;

    public Assignment() {
        this.assignment = new HashMap<>();
    }

    public boolean contains(String name) {
        return assignment.containsKey(name);
    }

    public T get(String key) {
        return  assignment.get(key);
    }

    public void put(String key, T value) {
        assignment.put(key, value);
    }
}
