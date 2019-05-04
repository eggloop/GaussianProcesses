/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package io.github.eggloop.simhya.simhya.script;


import io.github.eggloop.simhya.simhya.dataprocessing.TrajectoryExploratorDataCollector;

/**
 *
 * @author luca
 */
public class TrajectoryExplorationVariable extends ScriptVariable {
    TrajectoryExploratorDataCollector trajectoryExplorationData;

    public TrajectoryExplorationVariable(String name, TrajectoryExploratorDataCollector trajectoryExplorationData) {
        super(name);
        this.trajectoryExplorationData = trajectoryExplorationData;
    }

    @Override
    boolean isTrajectoryExplorationVariable() { return true; }
}
