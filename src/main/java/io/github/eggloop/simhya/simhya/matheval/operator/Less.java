/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package io.github.eggloop.simhya.simhya.matheval.operator;

import org.sbml.jsbml.ASTNode.Type;

/**
 *
 * @author Luca
 */
public class Less extends OperatorDefinition {

    public Less() {
        super("<");
    }

    public double compute(double x) {
        throw new UnsupportedOperationException("Operator " + symbol + " is binary");
    }
    public double compute(double x, double y) {
        if (x < y) return 1.0;
        else return 0.0;
    }

    @Override
    public Type getSBMLType() {
        return Type.RELATIONAL_LT;
    }
    
    @Override
    public boolean isRelational() {
        return true;
    }

    
    @Override
    public boolean isStrictInequality() {
        return true;
    }
}
