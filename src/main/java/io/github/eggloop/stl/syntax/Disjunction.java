package io.github.eggloop.stl.syntax;

public class Disjunction implements STL {

    private STL left;
    private STL right;

    public Disjunction(STL left, STL right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return left.toString() + " ∨ " + right.toString();
    }
}
