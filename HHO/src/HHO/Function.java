package HHO;

public class Function {
	
	private String name;
	private int lowerBound;
	private int upperBound;
	private int dimensions;
	
	public Function () {
		
	}
	
	public Function(String name, int lowerBound, int upperBound, int dimensions) {
		super();
		
		this.name = name;
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		this.dimensions = dimensions;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLowerBound() {
		return lowerBound;
	}
	public void setLowerBound(int lowerBound) {
		this.lowerBound = lowerBound;
	}
	public int getUpperBound() {
		return upperBound;
	}
	public void setUpperBound(int upperBound) {
		this.upperBound = upperBound;
	}
	public int getDimensions() {
		return dimensions;
	}
	public void setDimensions(int dimensions) {
		this.dimensions = dimensions;
	}

	@Override
	public String toString() {
		return "Function [name=" + name + ", lowerBound=" + lowerBound + ", upperBound=" + upperBound + ", dimensions="
				+ dimensions + "]";
	}
	
	
	
}
