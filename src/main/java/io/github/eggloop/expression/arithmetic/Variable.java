package io.github.eggloop.expression.arithmetic;

public class Variable implements ArithmeticExpression {

    private String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public AssignmentFunction compile() {
        return assignment -> assignment.get(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
