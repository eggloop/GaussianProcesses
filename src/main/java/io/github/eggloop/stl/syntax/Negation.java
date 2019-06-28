package io.github.eggloop.stl.syntax;

public class Negation {

    private STL argument;

    public Negation(STL argument) {
        this.argument = argument;
    }

    @Override
    public String toString() {
        return " ¬" + argument.toString();
    }
}
