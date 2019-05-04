package io.github.eggloop.gpoptim;

import io.github.eggloop.numeric.optimization.ObjectiveFunction;

public interface NoisyObjectiveFunction extends ObjectiveFunction {
	
	double getVarianceAt(double... point);

}
