/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package io.github.eggloop.simhya.simhya.script;

import io.github.eggloop.simhya.simhya.dataprocessing.DataCollector;

/**
 *
 * @author luca
 */
public class CollectorVariable extends ScriptVariable {
    DataCollector collector;

    public CollectorVariable(String name, DataCollector collector) {
        super(name);
        this.collector = collector;
    }

    @Override
    boolean isCollectorVariable() { return true; }
}
