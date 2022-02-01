package geometry;

/**
 * @author Itay Shwartz
 * This class represent point and the methods that we can apply on the point.
 */
public class Point {
    private final double x1;
    private final double y1;

    /**
     * This is the constructor method that get x and y ant put those numbers in to x1, y1.
     *
     * @param x .
     * @param y .
     */
    public Point(double x, double y) {
        this.x1 = x;
        this.y1 = y;
    }

    /**
     * This method return the distance between two points.
     *
     * @param other - another point.
     * @return the distance of this point to the other given point.
     */
    public double distance(Point other) {
        double x2 = other.getX();
        double y2 = other.getY();
        // We return the distance of two points according the Formula d²=(x1-x2)² + (y1-y2)².
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    /**
     * This method check if two point are equals.
     *
     * @param other - another point.
     * @return return true if the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        // To determine whether 2 points are equal - we compare their x and their y.
        if (x1 == other.getX() && y1 == other.getY()) {
            return true;
        }
        return false;
    }

    /**
     * This method return the x1 value of the point.
     *
     * @return x1
     */
    public double getX() {
        return x1;
    }

    /**
     * This method return the y1 value of the point.
     *
     * @return y1
     */
    public double getY() {
        return y1;
    }
}
