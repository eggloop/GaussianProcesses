/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package io.github.eggloop.simhya.simhya.script;

import io.github.eggloop.simhya.simhya.dataprocessing.StatisticsExploratorDataCollector;

/**
 *
 * @author luca
 */
public class StatisticsExplorationVariable extends ScriptVariable {
    StatisticsExploratorDataCollector statisticsExplorationData;

    public StatisticsExplorationVariable(String name, StatisticsExploratorDataCollector statisticsExplorationData) {
        super(name);
        this.statisticsExplorationData = statisticsExplorationData;
    }

    @Override
    boolean isStatisticsExplorationVariable() { return true; }



}
