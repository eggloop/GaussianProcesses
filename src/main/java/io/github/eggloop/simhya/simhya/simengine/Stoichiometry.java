/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.eggloop.simhya.simhya.simengine;

/**
 *
 * @author Luca
 */
public class Stoichiometry {
    public int variable;
    public double coefficient;

    public Stoichiometry(int variable, double coefficient) {
        this.variable = variable;
        this.coefficient = coefficient;
    }
}
