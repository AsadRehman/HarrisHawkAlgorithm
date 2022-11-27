package HHO;

public class HarrisHowkAlgorithm {

    Hawk[] hawks;
    Function selectedFunction = BenchMarks.getFunction(); // Select benchmark of your choice
    Rabbit rabbit = new Rabbit(new double[selectedFunction.getDimensions()], Double.POSITIVE_INFINITY, 0.f, 0.f);
    double[] convergenceCurve = new double[Constants._ITR];

	
 	public HarrisHowkAlgorithm() {
 		hawks = new Hawk[Constants._N];
 		HHO hho = new HHO();
 		hho.initializaion(hawks, selectedFunction);
 	    int counter=0;
 	    while(counter < Constants._ITR) {
 	    	//System.out.println("Iteration starts");
 	    	//print(hawks);
 	    	hho.checkBoundries(hawks, selectedFunction);
 	    	//System.out.println("After boundries");
 	    	//print(hawks);
 	    	hho.fitnessObj(hawks, selectedFunction);
 	    	for(int i=0; i<hawks.length; i++) {
 	    		if(hawks[i].getFitness() < rabbit.getEnergy()) {
 	    			rabbit.setEnergy(hawks[i].getFitness());
 	    			rabbit.setLocations(hawks[i].getPosition());
 	    		}
 	    	}
 	        double E0 = 2*(1-((double)counter/Constants._ITR));
 	    	rabbit.setDecreaingEnergy(E0); 
 	    	for(int i=0; i<hawks.length; i++) {
 	    		rabbit.setEscapingEnergy(rabbit.getDecreaingEnergy() * 2 * Math.random() - 1);
 	    		if(Math.abs(rabbit.getEscapingEnergy()) >= 1) {
 	    			double q = Math.random();
 	    			double[] randomSelectedHawkPositions = hawks[(int) Math.floor(Constants._N * Math.random())].getPosition();
 	    			if(q<0.5) {
 	    				hawks[i].setPosition(hho.perchBasedOnOtherFamilyMembers(randomSelectedHawkPositions, hawks[i].getPosition()));
 	    				//System.out.println("perchBasedOnOtherFamilyMembers");
 	    	 	    	//print(hawks);
 	    			} else {
 	    				hawks[i].setPosition(hho.perchBasedOnRandomTallTree(rabbit.getLocations(), hawks, selectedFunction.getUpperBound(), selectedFunction.getLowerBound()));
 	    				//System.out.println("perchBasedOnRandomTallTree");
 	    	 	    	//print(hawks);
 	    			}
 	    		} else  {
 	    			double r = Math.random();
 	    			if(r >= 0.5) {
 	    				if(Math.abs(rabbit.getEscapingEnergy()) < 0.5){
 	    					hawks[i].setPosition(hho.hardBeseige(rabbit.getLocations(), rabbit.getEscapingEnergy(), hawks[i].getPosition()));
 	    					//System.out.println("hardBeseige");
 	 	    	 	    	//print(hawks);
 	    				} else {
 	 	    				hawks[i].setPosition(hho.softBeseige(rabbit.getLocations(), rabbit.getEscapingEnergy(), hawks[i].getPosition()));
 	    					//System.out.println("softBeseige");
 	 	    	 	    	//print(hawks);
 	    				}
 	    			} else {
 	    				if(Math.abs(rabbit.getEscapingEnergy()) >= 0.5) {
 	    					hawks[i].setPosition(hho.teamRapidDivesSoftBesiege(rabbit.getLocations(), rabbit.getEscapingEnergy(), hawks[i].getPosition(), selectedFunction.getName()));
 	    					//System.out.println("teamRapidDivesSoftBesiege");
 	 	    	 	    	//print(hawks);
 	    				} else {
 	    					hawks[i].setPosition(hho.teamRapidDivesHardBesiege(rabbit.getLocations(), rabbit.getEscapingEnergy(), hawks[i].getPosition(), selectedFunction.getName(), hawks));
 	    					//System.out.println("teamRapidDivesHardBesiege");
 	 	    	 	    	//print(hawks);
 	    				}
 	    			}
 	    			
 	    		}
 	    		
 	    	}
 	    	convergenceCurve[counter] = rabbit.getEnergy();
 	 	    ++counter;
 	 	    if(counter % 1 == 0)
 	 	    System.out.println("At iteration " + counter + " the best fitness is " + rabbit.getEnergy());
 	    }
 	   
	}
	public static void main(String[] args) {
		HarrisHowkAlgorithm hha = new HarrisHowkAlgorithm();
	}

	public static void print(Hawk[] hawk) {
		System.out.println();
		for(int i = 0; i< hawk.length;i++) {
			for(int j = 0; j< hawk[i].getPosition().length;j++) {
				System.out.print(hawk[i].getPosition()[j] + "  ");
			}
			System.out.println();
		}
	}
}




