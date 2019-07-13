package io.github.eggloop.stl.syntax;

public class Until {

    private Interval interval;
    private Formula left;
    private Formula right;

    public Until(Interval interval, Formula left, Formula right) {
        this.interval = interval;
        this.left = left;
        this.right = right;
    }
}
