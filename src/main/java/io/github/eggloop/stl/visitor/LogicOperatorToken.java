package io.github.eggloop.stl.visitor;

public class LogicOperatorToken implements OperatorToken {

    @Override
    public String conjunction() {
        return "∧";
    }

    @Override
    public String disjunction() {
        return "∨";
    }

    @Override
    public String negation() {
        return "¬";
    }
}
