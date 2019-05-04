/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.eggloop.simhya.simhya.modelchecking;

import io.github.eggloop.simhya.simhya.simengine.SimType;
import io.github.eggloop.simhya.simhya.simengine.ode.IntegratorType;

/**
 *
 * @author luca
 */
public class SMCsimulationOptions {
    public double finalTime;
    public SimType simType = SimType.SSA; 
    public IntegratorType integrator = IntegratorType.DP85;
    public double stepSize = 0.1;
    
    public boolean continuousOutput() {
        return simType == SimType.HYBRID || simType == SimType.ODE || simType == SimType.LN;
    }
}
