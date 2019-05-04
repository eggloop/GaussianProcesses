package io.github.eggloop.sampler;


public interface GridSampler {

	double[][] sample(int n, double[] lbounds, double[] ubounds);
	double[][] sample(int n, Parameter[] params);

}
