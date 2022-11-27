package HHO;

public class Rabbit {

	private double[] locations;
	private double energy;
	private double decreaingEnergy;
	private double escapingEnergy;
	public Rabbit(double[] locations, double energy, double decreaingEnergy, double escapingEnergy) {
		super();
		this.locations = locations;
		this.energy = energy;
		this.decreaingEnergy = decreaingEnergy;
		this.escapingEnergy = escapingEnergy;
	}
	public double[] getLocations() {
		return locations;
	}
	public void setLocations(double[] locations) {
		this.locations = locations;
	}
	public double getEnergy() {
		return energy;
	}
	public void setEnergy(double energy) {
		this.energy = energy;
	}
	public double getDecreaingEnergy() {
		return decreaingEnergy;
	}
	public void setDecreaingEnergy(double decreaingEnergy) {
		this.decreaingEnergy = decreaingEnergy;
	}
	public double getEscapingEnergy() {
		return escapingEnergy;
	}
	public void setEscapingEnergy(double escapingEnergy) {
		this.escapingEnergy = escapingEnergy;
	}
	
	
	
	
}
