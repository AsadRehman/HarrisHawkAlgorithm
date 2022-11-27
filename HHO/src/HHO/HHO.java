package HHO;

import java.util.Random;

public class HHO {
	
	
	public void initializaion(Hawk[] hawks, Function f) {
        //For each particle
		int numDimensions = f.getDimensions();
		int lowerbound = f.getLowerBound();
		int upperbound = f.getUpperBound();
		
        for (int i=0; i<hawks.length;i++) {
            double[] positions = new double[numDimensions];
            for (int j=0; j<numDimensions; j++) {
                positions[j] = Math.random() * (upperbound - lowerbound) + lowerbound;
            }
            //Create the hawk
            hawks[i] = new Hawk(positions);
            //Set hawk position
        }
        
    }
	
	public void checkBoundries(Hawk[] hawks, Function f) {
		int numDimensions = f.getDimensions();
		int lowerbound = f.getLowerBound();
		int upperbound = f.getUpperBound();
		for (int i=0; i<hawks.length;i++) {
	        int[] falseUpperLimit = checkUpperBound(hawks[i], numDimensions, upperbound);
	        int[] falseLowerLimit = checkLowerBound(hawks[i], numDimensions, lowerbound);
	        hawks[i].setPosition(boundedFitness(hawks[i], falseUpperLimit, falseLowerLimit, upperbound, lowerbound,  numDimensions));
		}
	}
	
	private int[] checkUpperBound(Hawk hawk, int numDimensions, int upperbound) {
		int[] falseUpper = new int[numDimensions];
		for(int i=0; i<numDimensions; i++) {
			if(hawk.getPosition()[i] > Double.valueOf(upperbound)) {
				falseUpper[i] = 1;
			}
		}
		return falseUpper;
	}
	
	private int[] checkLowerBound(Hawk hawk, int numDimensions, int lowerbound) {
		int[] falseLower = new int[numDimensions];
		for(int i=0; i<numDimensions; i++) {
			if(hawk.getPosition()[i] < Double.valueOf(lowerbound)) {
				falseLower[i] = 1;
			}
		}
		return falseLower;
	}
	
	private double[] boundedFitness(Hawk hawk, int[] falseUpperLimit, int[] falseLowerLimit, int upperbound, int lowerbound, int numDimensions) {
		int[] negation = new int[numDimensions];
		for(int i=0; i<numDimensions; i++) {
			if(falseUpperLimit[i] + falseLowerLimit[i] == 0) {
				negation[i] = 1;
			} else {
				negation[i] = 0;
			}
		}
		double updatedPosition[] = new double[numDimensions];
		for(int i=0; i<numDimensions; i++) {
			updatedPosition[i] = (hawk.getPosition()[i] * negation[i]) + upperbound * falseUpperLimit[i] + lowerbound * falseLowerLimit[i];
		}
		return updatedPosition;
	}
	
	public void fitnessObj(Hawk[] hawks, Function f) {
		for (int i=0; i<hawks.length;i++) {
			hawks[i].setFitness(BenchMarks.fitness(f.getName(), hawks[i].getPosition()));
		}
	}
	
	
	public double[] perchBasedOnOtherFamilyMembers(double[] randomSelectedHawkPositions, double[] positions) {
		int dimensions = randomSelectedHawkPositions.length;
		double[] perchBasedOnOtherFamilyMembers = new double[dimensions];
		for(int i=0; i<dimensions; i++) {
			perchBasedOnOtherFamilyMembers[i] = randomSelectedHawkPositions[i] - Math.random() * Math.abs(randomSelectedHawkPositions[i] - 2 * Math.random()*positions[i]);
		}
		return perchBasedOnOtherFamilyMembers;
	}

	
	public double[] perchBasedOnRandomTallTree(double[] rabbitLocations, Hawk[] hawks, int upperbound, int lowerbound) {
		int dimensions = rabbitLocations.length;
		double[] perchBasedOnRandomTallTree = new double[dimensions];
		for(int i=0; i<dimensions; i++) {
			perchBasedOnRandomTallTree[i] = (rabbitLocations[i] - mean(hawks, i)) - Math.random() * ((upperbound-lowerbound)* Math.random()+ lowerbound);
		}               

		return perchBasedOnRandomTallTree;
	}
	

	private double mean(Hawk[] hawks, int dim) {
		double mean=0.f;
		for(int i=0; i<hawks.length; i++) {
			mean += hawks[i].getPosition()[dim]; 
		}
		return mean/hawks.length;
	}
	
	public double[] hardBeseige(double[] rabbitLocations, double escapingEnergy, double[] positions) {
		int dimensions = rabbitLocations.length;
		double[] hardBeseige = new double[dimensions];
		for(int i=0; i<dimensions; i++) {
			hardBeseige[i] = (rabbitLocations[i]) - escapingEnergy * Math.abs(rabbitLocations[i] - positions[i]) ;
		}
		return hardBeseige;
		
	}


	public double[] softBeseige(double[] rabbitLocations, double escapingEnergy, double[] positions) {
		double jumpStrength = 2 * (1 - Math.random());
		int dimensions = rabbitLocations.length;
		double[] softBeseige = new double[dimensions];
		
		for(int i=0; i<dimensions; i++) {
			softBeseige[i] = (rabbitLocations[i] - positions[i]) - escapingEnergy * Math.abs(jumpStrength * rabbitLocations[i] - positions[i]) ;
		}
		return softBeseige;
	}

	public double[] teamRapidDivesSoftBesiege(double[] rabbitLocations, double escapingEnergy, double[] positions, String name) {
		double jumpStrength = 2 * (1 - Math.random());
		int dimensions = rabbitLocations.length;
		double[] X1 = new double[dimensions];
		double[] X2 = new double[dimensions];
		for(int i=0; i<dimensions; i++) {
			X1[i] = rabbitLocations[i]  - escapingEnergy * Math.abs(jumpStrength * rabbitLocations[i] - positions[i]) ;
		} 
		double fitnessX1 = BenchMarks.fitness(name, X1);
		double fitnessPos = BenchMarks.fitness(name, positions);
		if(fitnessX1 < fitnessPos) {
			positions = X1;
		} else {
			double[] levy = levy(dimensions);
			for(int i=0; i<dimensions; i++) {
				X2[i] = X1[i] + (Math.random() * (dimensions + 1)) * levy[i];
			} 
			double fitnessX2 = BenchMarks.fitness(name, X2);
			if(fitnessX2 < fitnessPos) {
				positions = X2;
			}
		}
		return positions;
		
	}               

	
	

	
	public double[] levy(int dimensions) {
		double beta = 1.5;
		double[] u = new double[dimensions];
		double[] v = new double[dimensions];
		double[] step = new double[dimensions];
		
		double sigma = Math.pow(( gamma(1+beta) * Math.sin(Math.PI * beta / 2) / (gamma( (1 + beta) / 2) * beta * Math.pow(2, (beta -1)/2))), (1 / beta));
		
		for(int i=0; i<dimensions; i++) {
			Random rand = new Random();
			v[i] = dimensions * rand.nextGaussian();
			u[i] = dimensions * rand.nextGaussian() * sigma;
			step[i] = u[i]>0 ? Math.pow(u[i] / Math.abs(v[i]) , (1/beta)) : -1 * Math.pow(Math.abs(u[i]) / Math.abs(v[i]) , (1/beta));
		} 
		return step;
	}
	
	private static double logGamma(double x) {
	      double tmp = (x - 0.5) * Math.log(x + 4.5) - (x + 4.5);
	      double ser = 1.0 + 76.18009173    / (x + 0)   - 86.50532033    / (x + 1)
	                       + 24.01409822    / (x + 2)   -  1.231739516   / (x + 3)
	                       +  0.00120858003 / (x + 4)   -  0.00000536382 / (x + 5);
	      return tmp + Math.log(ser * Math.sqrt(2 * Math.PI));
	   }
	public static double gamma(double x) { return Math.exp(logGamma(x)); }

	public double[] teamRapidDivesHardBesiege(double[] rabbitLocations, double escapingEnergy, double[] positions,
			String name, Hawk[] hawks) {
		double jumpStrength = 2 * (1 - Math.random());
		int dimensions = rabbitLocations.length;
		double[] X1 = new double[dimensions];
		double[] X2 = new double[dimensions];
		for(int i=0; i<dimensions; i++) {
			X1[i] = rabbitLocations[i]  - escapingEnergy * Math.abs(jumpStrength * rabbitLocations[i] - mean(hawks, i)) ;
		} 
		double fitnessX1 = BenchMarks.fitness(name, X1);
		double fitnessPos = BenchMarks.fitness(name, positions);
		if(fitnessX1 < fitnessPos) {
			positions = X1;
		} else {
			double[] levy = levy(dimensions);
			for(int i=0; i<dimensions; i++) {
				X2[i] = X1[i] + (Math.random() * (dimensions + 1)) * levy[i];
			} 
			double fitnessX2 = BenchMarks.fitness(name, X2);
			if(fitnessX2 < fitnessPos) {
				positions = X2;
			}
		}
		return positions;
	}

	
}



