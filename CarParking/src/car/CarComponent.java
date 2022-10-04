package car;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JComponent;

/**
 * A component that shows a scene composed of items.
 */
@SuppressWarnings("serial")
public class CarComponent extends JComponent {
	List<Car> cars; // instantiate car list
	int totalCars; // total cars

	// constructor of carcomponent
	public CarComponent() {
		Scanner sc = new Scanner(System.in); // System.in is a standard input stream.
		// prompt user to input car
		System.out.print("How may cars are racing [Min 3, Max 5] : ");
		totalCars = sc.nextInt();
		if (totalCars > 5)
			totalCars = 5;
		if (totalCars < 3)
			totalCars = 3;

		System.out.printf("%d cars are racing.", totalCars);
		// create car list and add cars in the list
		cars = new ArrayList<>();
		for (int i = 0; i < totalCars; i++) {
			add(new Car(0, 10 + (i * Constants.FRAME_HEIGHT / totalCars)));
		}
	}

	// this method will add cars in array list
	public boolean add(Car car) {
		return cars.add(car);
	}

	// this method will move the cars
	public void moveCars() {
		int dx = 1;
		int dy = 0;
		// move the cars in forward and backword direction
		for (int i = 0; i < totalCars; i++) {
			Random random = new Random();
			dx = random.nextInt(5) + 1; // random movement for each car will make cars running with different speed
			// when cars are moving in forward direction, increase dx
			if (cars.get(i).isForward()) {
				cars.get(i).translate(dx, dy);
				repaint();
			} else { // otherwise move card in reverse direction
				cars.get(i).translate(-dx, dy);
				repaint();
			}
			// reverse the direction when car reach at the end of screen.
			if (cars.get(i).getxLeft() > Constants.FRAME_WIDTH - 50) {
				cars.get(i).setForward(false);
			}
			// set direction forward when car reach at the beginning of screen.
			if (cars.get(i).getxLeft() < 1) {
				cars.get(i).setForward(true);
			}
			dx = random.nextInt(5) + 1;
		}

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		// This will draw all the cars on the frame
		for (int i = 0; i < totalCars; i++) {
			cars.get(i).draw(g2);
		}

	}

}