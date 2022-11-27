package HHO;

public class Hawk {
	double[] position; //The position vector of this Hawk
	double fitness; //The fitness of this Hawk

	public Hawk(double[] position) {
		this.position = position;
	}

	public double[] getPosition() {
		return position;
	}

	public void setPosition(double[] position) {
		this.position = position;
	}

	public double getFitness() {
		return fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
	
	
}
