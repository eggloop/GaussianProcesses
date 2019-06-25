package io.github.eggloop.expression;

public class Variable implements ArithmeticExpression {
    private String name;

    public Variable(String name) {
        this.name = name;
    }


    @Override
    public ArithmeticExpression evaluate(Assignment assignment) {
        if (assignment.contains(name)) {
            return new Constant(assignment.get(name));
        }
        return this;
    }
}
