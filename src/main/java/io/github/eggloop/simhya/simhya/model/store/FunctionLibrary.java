/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package io.github.eggloop.simhya.simhya.model.store;

import io.github.eggloop.simhya.simhya.matheval.SymbolArray;

import java.util.ArrayList;


/**
 *
 * @author Luca
 */
public interface FunctionLibrary {

    public boolean isLibraryFunction(String name, int args);
    public Function getLibraryFunction(String name, ArrayList<String> args, Store store);
    public Function getLibraryFunction(String name, ArrayList<String> args, Store store, SymbolArray localVars);
    public boolean isArgumentCorrect(String name, int index, String arg, Store store);
    public boolean isArgumentCorrect(String name, int index, String arg, Store store, SymbolArray localVars);
    public String getArgumentType(String name, int index);
}
