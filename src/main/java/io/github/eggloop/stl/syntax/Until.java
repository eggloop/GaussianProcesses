package io.github.eggloop.stl.syntax;

public class Until {

    private Interval interval;
    private STL left;
    private STL right;

    public Until(Interval interval, STL left, STL right) {
        this.interval = interval;
        this.left = left;
        this.right = right;
    }
}
