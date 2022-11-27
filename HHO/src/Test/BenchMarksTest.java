package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import HHO.BenchMarks;
import HHO.HHO;

class BenchMarksTest {
	
	BenchMarks benchMark = new BenchMarks();
	HHO hho = new HHO();
	
	@Test
	void testF1() {
		double positions[] = {0.34, -0.1, 0.78, 0.99};
		double ftnValue = benchMark.fitnessF1(positions);
		Assertions.assertEquals(1.7141000000000002, ftnValue);
	}
	
	@Test
	void testF2() {
		double positions[] = {0.8, -0.4, 0.99, -0.59, 0.2, 0.45};
		double ftnValue = benchMark.fitnessF2(positions);
		Assertions.assertEquals(3.4300000000000006, ftnValue);
	}

	@Test
	void gamma() {
		double beta = 1.5;
		double gamma = hho.gamma(1+ beta);
		
		Assertions.assertEquals(1.3293403880407133, gamma);

	}
	
	@Test
	void fitness1() {
		double[] pos =  {49.44490159015811, 93.76938490316775, 67.72639766009712, 21.136802896422125, -57.40485502346901, 28.219460858194083, -66.7581695045798, -25.175544358323293, -19.033770503172676, 68.46879574843584, -0.8074768368516771, 68.93243245035481, -57.03808419700893, -81.52613480671742, -62.27109602325771, 35.071296900762974, -59.8771407487592, 11.955363668555947, 34.465243926895994, 24.66523739362016, 47.82525891678972, 61.771563762422005, -94.05174170393855, 0.2749643630763501, 18.322209723194007, 52.640537026585946, -58.09687864518798, 58.72469436896597, 51.6710051265656, 99.8736410429924};
		double f = BenchMarks.fitnessF1(pos);
		System.out.println(f);
		Assertions.assertEquals(93311.79381358885, f);
	}

}
