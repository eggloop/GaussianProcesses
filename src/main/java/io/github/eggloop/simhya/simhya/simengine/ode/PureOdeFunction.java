/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package io.github.eggloop.simhya.simhya.simengine.ode;

import org.apache.commons.math.ode.DerivativeException;
import io.github.eggloop.simhya.simhya.model.flat.FlatModel;
import io.github.eggloop.simhya.simhya.matheval.SymbolArray;
import io.github.eggloop.simhya.simhya.simengine.Stoichiometry;

/**
 * This is the ODE function for a model in which all transitions are
 * continuously approximable and unguarded.
 * @author luca
 */
public class PureOdeFunction extends OdeFunction {
    SymbolArray vars;

    public PureOdeFunction(FlatModel model) {
        super(model);
        //sets up the SymboArray dats structure for evaluation of flows
        vars = SymbolArray.getNewFastEvalSymbolArray(this.dimension);
    }

    public void computeDerivatives(double t, double[] y, double[] yDot) throws DerivativeException {
        //resets derivatives to zero
        java.util.Arrays.fill(yDot, 0.0);
        //assign correct value to variables
        vars.setValuesReference(y);
        for (int i=0;i<this.continuousTransitions;i++) {
            int j = this.transitions[i];
            double x = useCache ? rate[j].computeCache(vars) :
                                  rate[j].compute(vars);
            for (Stoichiometry s : stoich[j])
                yDot[s.variable] += s.coefficient * x;
        }
        //sanity check
        for (int i=0;i<yDot.length;i++) {
            if (Double.isInfinite(yDot[i]) || Double.isNaN(yDot[i]))
                throw new DerivativeException("Variable " + i + " has infinite or NaN derivative");
        }
        
    }

    public void updateGuardStatus(int firedTransition, double t, double[] y) {
        
    }

    


}
