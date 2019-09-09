package io.github.eggloop.spider;

import com.sun.xml.internal.ws.developer.Serialization;
import io.github.eggloop.stl.syntax.Formula;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanBest implements Serializable {
    private Map<Formula, List<double[]>> storage;

    public BeanBest() {
        storage = new HashMap<>();
    }

    public void put(Formula formula, List<double[]> parameters) {
        storage.put(formula, parameters);
    }

    public Map<Formula, List<double[]>> getStorage() {
        return storage;
    }
}
