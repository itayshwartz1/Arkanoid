//318528171
package geometry;

import biuoop.DrawSurface;
import sprites.Sprite;

import java.awt.Color;

/**
 * @author Itay Shwartz
 * This class represent Circle.
 */
public class Circle implements Sprite {
    private Point center;
    private double radius;
    private Color color;
    private boolean toDraw;

    /**
     * This is the constructor for Circle.
     *
     * @param center - the center of the circle
     * @param radius - the radius of the circle
     * @param color  - the color of the circle
     * @param toDraw - to draw or to fill
     */
    public Circle(Point center, int radius, Color color, boolean toDraw) {
        this.center = center;
        this.radius = radius;
        this.color = color;
        this.toDraw = toDraw;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        // We draw the circle or fill it according toDraw that passed to us.
        if (this.toDraw) {
            d.drawCircle((int) this.getCenterX(), (int) this.getCenterY(), (int) this.radius);
        } else {
            d.fillCircle((int) this.getCenterX(), (int) this.getCenterY(), (int) this.radius);
        }
    }

    @Override
    public void timePassed() {

    }

    /**
     * This method is a getter to the x value of the circle center.
     *
     * @return -  double x
     */
    public double getCenterX() {
        return this.center.getX();
    }

    /**
     * This method is a getter to the y value of the circle center.
     *
     * @return -  double y
     */
    public double getCenterY() {
        return this.center.getY();
    }
}
