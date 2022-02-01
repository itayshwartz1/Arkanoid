//318528171

package geometry;

/**
 * @author Itay Shwartz
 * This class describe the object geometry.Velocity.
 */
public class Velocity {
    // geometry.Velocity specifies the change in position on the `x` and the `y` axes.
    private double dx;
    private double dy;

    /**
     * This method is the constructor of the object geometry.Velocity. it get dx and dy and from them it get the angle.
     *
     * @param dx - the change in position on the `x`
     * @param dy - the change in position on the `y`
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * This method give new velocity from angle and "speed" unit.
     *
     * @param angle - the direction of the geometry.Velocity.
     * @param speed - the speed that the object move.
     * @return geometry.Velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        // We change the angle from degrees to radian.
        angle = Math.toRadians(angle);
        // According to the calculations of a right angle triangle with sin and cos we get the following formulas:
        double dx = Math.sin(angle) * speed;
        double dy = -Math.cos(angle) * speed;
        // We create a new geometry.Velocity
        return new Velocity(dx, dy);
    }

    /**
     * This method return the change in the x according the velocity - dx.
     *
     * @return dx
     */
    public double getDx() {
        return dx;
    }

    /**
     * This method return the change in the y according the velocity - dy.
     *
     * @return dy.
     */
    public double getDy() {
        return dy;
    }

    /**
     * this method change the velocity of the ball according given x, y - the dx and dy.
     *
     * @param x - the change int the x - dx.
     * @param y - the change int the y - dy.
     */
    public void changeVelocity(double x, double y) {
        this.dx = x;
        this.dy = y;
    }

    /**
     * This method take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     *
     * @param p - point.
     * @return geometry.Point.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }
}