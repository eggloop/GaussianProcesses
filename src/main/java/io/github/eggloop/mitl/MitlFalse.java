package io.github.eggloop.mitl;


import io.github.eggloop.model.Trajectory;

public final class MitlFalse extends MiTL {

	@Override
	public boolean evaluate(Trajectory x, double t) {
		return false;
	}

    @Override
    public double evaluateValue(Trajectory x, double t) {
        return -Double.MAX_VALUE;
    }

	@Override
	public MiTL clone() {
		return new MitlFalse();
	}

	@Override
	public String toString() {
		return "false";
	}

}
