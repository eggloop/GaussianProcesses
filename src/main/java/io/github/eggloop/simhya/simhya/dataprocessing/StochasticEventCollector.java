/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package io.github.eggloop.simhya.simhya.dataprocessing;

/**
 *
 * @author luca
 */
public interface StochasticEventCollector extends EventDataCollector {
    public void putNextStochasticEvent(double time, double[] y, String name);
}
