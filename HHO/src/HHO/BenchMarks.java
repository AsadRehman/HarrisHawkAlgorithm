package HHO;

public class BenchMarks {
	
	public static Function F1(String name) {
		return new Function(name, -100,100,30);
	}
	
	public static Function F2(String name) {
		return new Function(name, -10,10,30);
	}
	
	public static double fitnessF1(double[] positions) {
		double functionValue = 0.f;
		for(int i=0; i<positions.length;i++) {
			functionValue += positions[i]*positions[i];
		}
		return functionValue;
	}
	
	public static double fitnessF2(double[] positions) {
		double sum = 0.f;
		double prod = 0.f;
		for(int i=0; i<positions.length;i++) {
			sum += Math.abs(positions[i]);
		}
		for(int i=0; i<positions.length;i++) {
			prod *= Math.abs(positions[i]);
		}
		return sum + prod;
	}
	
	public static Function getFunction() {
		String name = Constants.SELECTED_FUNCTION_NAME;
		switch(name) {
		case "F1":
			return F1(name);
		case "F2":
			return F2(name);
		default:
			return F1(name);
		}
	}
	
	public static double fitness(String name, double[] positions) {
		switch(name) {
		case "F1":
			return fitnessF1(positions);
		case "F2":
			return fitnessF2(positions);
		default:
			return fitnessF1(positions);
		}
	}
}
