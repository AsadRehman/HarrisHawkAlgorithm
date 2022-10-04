package car;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.Timer;

import javax.swing.JFrame;

/**
 * A program that allows users to move a car.
 */
public class CarMover {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame(); // instantiate java frame
		frame.setTitle("Car Moving"); // set title of frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // main thread will destroy on close

		CarComponent component = new CarComponent(); // instantiate car component object
		frame.add(component); // add car component object in frame
		frame.setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT); // set frame height and width
		frame.setVisible(true); // make frame visible

		// Inner class TimeListener
		class TimeListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) { // Action performed method will perform action defined
				component.moveCars(); // Can will start moving
				component.repaint(); // when change positioin, it will paint again at new position
			}
		}
		ActionListener listener = new TimeListener(); // instantiate time listener
		Scanner sc = new Scanner(System.in); // System.in is a standard input stream.
		final int DELAY; // delay in moving car
		System.out.println("\nEnter time delay (in milliseconds) : "); // prompt user to enter delay
		DELAY = sc.nextInt();
		Timer t = new Timer(DELAY, listener);
		t.start(); // It will start the motion of cars
	}

}
