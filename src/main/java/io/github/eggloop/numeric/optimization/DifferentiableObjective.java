package io.github.eggloop.numeric.optimization;

public interface DifferentiableObjective extends ObjectiveFunction {

	double[] getGradientAt(double... point);

}
