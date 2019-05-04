/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.eggloop.simhya.simhya.modelchecking;

/**
 *
 * @author luca
 */
public class BetaPrior {

    public BetaPrior() {
        this.alpha = 1;
        this.beta = 1;
    }

   
    public BetaPrior(double alpha, double beta) {
        this.alpha = alpha;
        this.beta = beta;
    }
     
    public double alpha;
    public double beta;
    
  
}
