package io.github.eggloop.spider;

import java.io.Serializable;
import java.util.List;

public class Bean implements Serializable {
    private List<double[]> eventually;
    private List<double[]> globally;

    Bean(List<double[]> eventually, List<double[]> globally) {
        this.eventually = eventually;
        this.globally = globally;
    }

    public List<double[]> getEventually() {
        return eventually;
    }

    public List<double[]> getGlobally() {
        return globally;
    }
}
