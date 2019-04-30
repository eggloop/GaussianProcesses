package io.github.eggloop.gaussianprocesses.gp;

import io.github.eggloop.gaussianprocesses.gp.kernels.Kernel;
import io.github.eggloop.numeric.algebra.linalg.IAlgebra;
import io.github.eggloop.numeric.algebra.linalg.JblasAlgebra;
import io.github.eggloop.numeric.algebra.linalg.NonPosDefMatrixException;

public abstract class AbstractGP<PosteriorType extends GpPosterior> {

	final protected IAlgebra algebra;
	final private Kernel kernel;
	protected GpDataset trainingSet = new GpDataset(1);

	public AbstractGP(Kernel kernel) {
		this(new JblasAlgebra(), kernel);
	}

	public AbstractGP(IAlgebra algebra, Kernel kernel) {
		this.algebra = algebra;
		this.kernel = kernel;
	}

	public Kernel getKernel() {
		return kernel;
	}

	public GpDataset getTrainingSet() {
		return trainingSet;
	}

	public void setTrainingSet(GpDataset trainingSet) {
		this.trainingSet = trainingSet;
	}

	abstract public PosteriorType getGpPosterior(GpDataset testSet)
			throws NonPosDefMatrixException;

	abstract public double getMarginalLikelihood()
			throws NonPosDefMatrixException;

	abstract public double[] getMarginalLikelihoodGradient()
			throws NonPosDefMatrixException;

}
