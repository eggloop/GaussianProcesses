package io.github.eggloop.stl.syntax;

public class Negation implements STL {

    private STL argument;

    public Negation(STL argument) {
        this.argument = argument;
    }

    @Override
    public String toString() {
        return SyntaxUtils.toStringUnaryFormula("!", argument.toString());
    }

    @Override
    public String toLogicString() {
        return SyntaxUtils.toStringUnaryFormula("¬", argument.toString());
    }
}
