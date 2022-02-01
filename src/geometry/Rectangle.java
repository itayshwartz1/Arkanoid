//318528171

package geometry;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Itay Shwartz
 * This is the geometry.Rectangle class.
 */
public class Rectangle {
    private final Point upperLeft;
    private final double width;
    private final double height;
    private final Line upLine;
    private final Line downLine;
    private final Line leftLine;
    private final Line rightLine;


    /**
     * This it the constructor of the object geometry.Rectangle.
     *
     * @param upperLeft - the "starting" point of the rectangle.
     * @param width     - the width of the rectangle.
     * @param height    - the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        double x = upperLeft.getX();
        double y = upperLeft.getY();
        // We set all the lines of the edge of the rectangle.
        this.upLine = new Line(x, y, x + width, y);
        this.downLine = new Line(x, y + height, x + width, y + height);
        this.leftLine = new Line(x, y, x, y + height);
        this.rightLine = new Line(x + width, y, x + width, y + height);
    }


    /**
     * This method return a list with all the intersection point with given line to the edges of the rectangle.
     *
     * @param line - the line that we check if it intersect.
     * @return - list of intersection point (possibly empty).
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> intersectList = new ArrayList<>();
        // We check fot intersection with all the edges of the rectangle
        if (this.upLine.intersectionWith(line) != null) {
            intersectList.add(this.upLine.intersectionWith(line));
        }
        if (this.downLine.intersectionWith(line) != null) {
            intersectList.add(this.downLine.intersectionWith(line));
        }
        if (this.leftLine.intersectionWith(line) != null) {
            intersectList.add(this.leftLine.intersectionWith(line));
        }
        if (this.rightLine.intersectionWith(line) != null) {
            intersectList.add(this.rightLine.intersectionWith(line));
        }
        return intersectList;
    }


    /**
     * This method return the width of the rectangle.
     *
     * @return - width.
     */
    public double getWidth() {
        return this.width;
    }


    /**
     * This method return the height of the rectangle.
     *
     * @return - height.
     */
    public double getHeight() {
        return this.height;
    }


    /**
     * This method return the upperLeft point of the rectangle.
     *
     * @return - upperLeft point.
     */
    // Returns the upper-left point of the rectangle.
    public Point getUpperLeft() {
        return this.upperLeft;
    }


    /**
     * This method draw a fill rectangle on a given surface.
     *
     * @param surface - the surface that we draw on.
     */
    public void drawOn(DrawSurface surface) {
        // We fill a rectangle in the surface according to the rectangle data.
        surface.fillRectangle((int) this.getUpperLeft().getX(), (int) this.getUpperLeft().getY(),
                (int) this.getWidth(), (int) this.getHeight());
    }


    /**
     * This method return th upLine of the rectangle.
     *
     * @return - geometry.Line.
     */
    public Line getUpLine() {
        return upLine;
    }


    /**
     * This method return th downLine of the rectangle.
     *
     * @return - geometry.Line.
     */
    public Line getDownLine() {
        return downLine;
    }


    /**
     * This method return th leftLine of the rectangle.
     *
     * @return - geometry.Line.
     */
    public Line getLeftLine() {
        return leftLine;
    }


    /**
     * This method return th rightLine of the rectangle.
     *
     * @return - geometry.Line.
     */
    public Line getRightLine() {
        return rightLine;
    }
}
