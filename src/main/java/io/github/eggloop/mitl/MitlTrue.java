package io.github.eggloop.mitl;


import io.github.eggloop.model.Trajectory;

public final class MitlTrue extends MiTL {

	@Override
	public boolean evaluate(Trajectory x, double t) {
		return true;
	}

    @Override
    public double evaluateValue(Trajectory x, double t) {
        return Double.MAX_VALUE;
    }

	@Override
	public MiTL clone() {
		return new MitlTrue();
	}

	@Override
	public String toString() {
		return "true";
	}

}
