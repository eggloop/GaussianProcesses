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
public class Plus extends OperatorDefinition {

    public Plus() {
        super("+");
    }

    public double compute(double x) {
        return x;
    }
    public double compute(double x, double y) {
        return x+y;
    }

    @Override
    public Type getSBMLType() {
        return Type.PLUS;
    }
    
    

}
