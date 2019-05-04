/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package io.github.eggloop.simhya.simhya.script;

import io.github.eggloop.simhya.simhya.model.flat.FlatModel;

/**
 *
 * @author luca
 */
public class ModelVariable extends ScriptVariable {
    FlatModel model;

    ModelVariable(String name, FlatModel model) {
        super(name);
        this.model = model;
    }

    
    @Override
    boolean isModelVariable() { return true; }
}
