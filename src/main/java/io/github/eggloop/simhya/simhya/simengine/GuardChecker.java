/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.eggloop.simhya.simhya.simengine;

/**
 *
 * @author Luca
 */
public interface GuardChecker {
    
    public void updateGuardStatus(int firedTransition, double t, double[] y);
    
}
