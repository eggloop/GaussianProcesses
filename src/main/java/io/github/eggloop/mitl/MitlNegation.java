package io.github.eggloop.mitl;


import io.github.eggloop.model.Trajectory;

public final class MitlNegation extends MiTL {

	final private MiTL formula;

	public MitlNegation(final MiTL formula) {
		this.formula = formula;
	}

	@Override
	public boolean evaluate(Trajectory x, double t) {
		return !this.formula.evaluate(x, t);
	}

    @Override
    public double evaluateValue(Trajectory x, double t) {
        return -formula.evaluateValue(x, t);
    }

	@Override
	public MiTL clone() {
		return new MitlNegation(formula.clone());
	}

	@Override
	public String toString() {
		return "!(" + formula + ")";
	}

}
