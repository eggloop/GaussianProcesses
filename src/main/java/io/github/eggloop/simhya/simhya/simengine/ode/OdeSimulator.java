/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package io.github.eggloop.simhya.simhya.simengine.ode;

import io.github.eggloop.simhya.simhya.dataprocessing.OdeDataCollector;
import io.github.eggloop.simhya.simhya.simengine.SimulationException;
import io.github.eggloop.simhya.simhya.model.flat.FlatModel;
import io.github.eggloop.simhya.simhya.model.flat.LinearNoiseFlatModel;

/**
 *
 * @author luca
 */
public class OdeSimulator extends AbstractOdeSimulator {

    public OdeSimulator(FlatModel model, OdeDataCollector collector) {
        super(model, collector);
        //ignore delays and continue
//        if (model.containsDelayedTransitions())
//            throw new SimulationException("Ode simulators do not support delays");
        if (model.containsInstantaneousTransitions() || model.containsTimedTransitions())
            throw new SimulationException("Wrong integrator");
        if (model.containsNonContinuouslyApproximableStochasticTransitions())
            throw new SimulationException("There are stochastic transitions not continously approximable, use hybrid simulator");
        if (model.isLinearNoiseApproximation()) {
            LinearNoiseFlatModel lnModel = (LinearNoiseFlatModel)model;
            this.function = new LinearNoiseOdeFunction(lnModel);
        }
        else if (model.containsGuardedTransitions())
            this.function = new GuardedOdeFunction(model);
        else
            this.function = new PureOdeFunction(model);
    }

    public void initialize() {
        integrator = super.newIntegrator();
        integrator.addStepHandler(this);
        initialized = true;
        collector.collectEventData(false);
    }

    public void reinitialize() {
        integrator = super.newIntegrator();
        integrator.addStepHandler(this);
        initialized = true;
        collector.collectEventData(false);
    }





    


}
