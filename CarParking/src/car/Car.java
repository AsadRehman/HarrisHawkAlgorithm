package car;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
   A car shape.
 */
public class Car
{
  private int xLeft; //position at left horizontal
  private int yTop; //position at top vertical
  private boolean forward; //direction
  
  // Car construction
  public Car(int x, int y)
  {
    this.xLeft = x;
    this.yTop = y;
    this.forward = true; 
  }

  // This will draw the car on frame
  public void draw(Graphics2D g2)
  {
	  int width = 50;
	  Rectangle2D.Double body
	  = new Rectangle2D.Double(xLeft, yTop + width / 6,
	  width - 1, width / 6);
	  Ellipse2D.Double frontTire
	  = new Ellipse2D.Double(xLeft + width / 6, yTop + width / 3,
	  width / 6, width / 6);
	  Ellipse2D.Double rearTire
	  = new Ellipse2D.Double(xLeft + width * 2 / 3, yTop + width / 3,
	  width / 6, width / 6);

	  // The bottom of the front windshield
	  Point2D.Double r1
	  = new Point2D.Double(xLeft + width / 6, yTop + width / 6);
	  // The front of the roof
	  Point2D.Double r2
	  = new Point2D.Double(xLeft + width / 3, yTop);
	  // The rear of the roof
	  Point2D.Double r3
	  = new Point2D.Double(xLeft + width * 2 / 3, yTop);
	  // The bottom of the rear windshield
	  Point2D.Double r4
	  = new Point2D.Double(xLeft + width * 5 / 6, yTop + width / 6);
	  Line2D.Double frontWindshield
	  = new Line2D.Double(r1, r2);
	  Line2D.Double roofTop
	  = new Line2D.Double(r2, r3);
	  Line2D.Double rearWindshield
	  = new Line2D.Double(r3, r4);

	  g2.draw(body);
	  g2.draw(frontTire);
	  g2.draw(rearTire);
	  g2.draw(frontWindshield);
	  g2.draw(roofTop);
	  g2.draw(rearWindshield);
  }

  public void translate(int dx, int dy)
  {
    xLeft += dx;
    yTop += dy;
  }

  public boolean isForward() {
	return forward;
  }

  public void setForward(boolean forward) {
	this.forward = forward;
  }

  public int getxLeft() {
	return xLeft;
  }

  public int getyTop() {
	return yTop;
  }
}