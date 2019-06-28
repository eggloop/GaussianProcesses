package io.github.eggloop.expression.arithmetic;

public class Variable implements ArithmeticExpression {

    private String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public double evaluate(Assignment assignment) throws VariableException {
        if (assignment.contains(name)) {
            return assignment.get(name);
        }
        throw new VariableException(String.format("MISSING VARIABLES %s", name));
    }

    @Override
    public String toString() {
        return name;
    }
}
