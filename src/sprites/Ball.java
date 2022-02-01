//318528171

package sprites;

import biuoop.DrawSurface;
import collidable.CollisionInfo;
import game.GameLevel;
import game.GameEnvironment;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;

import java.awt.Color;

/**
 * @author Itay Shwartz
 * This class describe the object ball.
 */
public class Ball implements Sprite {
    private Point center;
    private final double r;
    private final java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;
    private static final double ALMOST_HIT = 4;


    /**
     * This method construct a new ball.
     *
     * @param center - the center of the ball.
     * @param r      - the radius of the ball.
     * @param color  - the color of the ball.
     */
    public Ball(Point center, double r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
        // Until someone put inside the ball velocity we put inside it simple velocity.
        this.velocity = new Velocity(1, 1);
    }


    /**
     * This method construct a new ball.
     *
     * @param x     - the x location of the center of the ball.
     * @param y     - the y location of the center of the ball.
     * @param r     - the radius of the ball.
     * @param color - the color of the ball.
     */
    public Ball(double x, double y, double r, java.awt.Color color) {
        // We create a new point from the x and y we received and put the point in the center of the ball.
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        // We put some simple velocity.
        this.velocity = new Velocity(1, 1);
        // We swt the ball frame limits.
    }


    /**
     * This method return the x value of the center of the ball.
     *
     * @return the x value of the center of the ball.
     */
    public double getX() {
        return center.getX();
    }


    /**
     * This method return the y value of the center of the ball.
     *
     * @return the y value of the center of the ball.
     */
    public double getY() {
        return center.getY();
    }


    /**
     * This method return the radius of the ball.
     *
     * @return the radius of the ball.
     */
    public double getSize() {
        return r;
    }


    /**
     * This method return the color of the ball.
     *
     * @return color.
     */
    public java.awt.Color getColor() {
        return color;
    }


    /**
     * Those method return the velocity of the ball.
     *
     * @return velocity.
     */
    public Velocity getVelocity() {
        return velocity;
    }


    /**
     * This method draw the ball on the given DrawSurface.
     *
     * @param surface - the surface that we draw the ball.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle((int) center.getX(), (int) center.getY(), (int) r);
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) center.getX(), (int) center.getY(), (int) r);
    }


    /**
     * This method put inside the velocity of the ball given velocity.
     *
     * @param v - velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }


    /**
     * This method swt to the ball velocity according given dx and dy.
     *
     * @param dx .
     * @param dy .
     */
    public void setVelocity(double dx, double dy) {
        // We create a new velocity from the given parameters and put the velocity inside the ball.
        this.velocity = new Velocity(dx, dy);
    }


    /**
     * This method check if the ball is inside the paddle.
     *
     * @return true / false - if the ball inside the paddle or not.
     */
    private boolean isPointInsidePaddle() {
        Paddle paddle = gameEnvironment.getPaddle();
        double xPaddle = paddle.getCollisionRectangle().getUpperLeft().getX();
        double yPaddle = paddle.getCollisionRectangle().getUpperLeft().getY();
        if (this.getX() > xPaddle && this.getX() < xPaddle + paddle.getPaddleWidth()
                && this.getY() > yPaddle && this.getY() < yPaddle + paddle.getPaddleHeight()) {
            return true;
        }
        return false;
    }

    /**
     * This method change the center point of the ball according his velocity and where it hit.
     */
    public void moveOneStep() {
        double yPaddle = gameEnvironment.getPaddle().getCollisionRectangle().getUpperLeft().getY();
        if (isPointInsidePaddle()) {
            this.center = new Point(this.getX(), yPaddle - 1);
        }
        // First we compute the trajectory of the ball - where it will be in the next step.
        Line trajectory = new Line(this.getX(), this.getY(),
                this.getX() + this.getVelocity().getDx(), this.getY() + this.getVelocity().getDy());
        CollisionInfo collisionInfo = this.gameEnvironment.getClosestCollision(trajectory);
        // If there is no hit - we move the center of the ball according the trajectory
        if (collisionInfo == null) {
            this.center = trajectory.end();
        } else {
            // We need to check were is the hit point so we can move the ball "almost" to the hit point.
            Point hitPoint = collisionInfo.collisionPoint();
            Rectangle rectangle = collisionInfo.collisionObject().getCollisionRectangle();
            Line upLine = rectangle.getUpLine();
            Line downLine = rectangle.getDownLine();
            Line leftLine = rectangle.getLeftLine();
            Line rightLine = rectangle.getRightLine();
            double dx = this.getVelocity().getDx();
            double dy = this.getVelocity().getDy();
            // If the ball hit in the left top corner of the block.
            if (upLine.isPointOnTheLine(hitPoint) && leftLine.isPointOnTheLine(hitPoint)) {
                // We set his "almost" hit point according the direction (m) that the ball coming from.
                if (trajectory.getM() == Double.POSITIVE_INFINITY) {
                    this.center = new Point(this.center.getX() + dx, this.center.getY() + dy - ALMOST_HIT);
                } else if (trajectory.getM() > 0) {
                    this.center = new Point(this.center.getX() + dx - ALMOST_HIT,
                            this.center.getY() + dy - ALMOST_HIT);
                } else if (trajectory.getM() == 0) {
                    this.center = new Point(this.center.getX() + dx - ALMOST_HIT, this.center.getY() + dy);
                    // If the trajectory have a negative m - it could come from above the up line or left from leftLine.
                } else if (trajectory.getM() < 0 && trajectory.start().getY() > upLine.start().getY()) {
                    this.center = new Point(this.center.getX() + dx + ALMOST_HIT,
                            this.center.getY() + dy - ALMOST_HIT);
                } else if (trajectory.getM() < 0 && trajectory.start().getX() < leftLine.start().getX()) {
                    this.center = new Point(this.center.getX() + dx - ALMOST_HIT,
                            this.center.getY() + dy + ALMOST_HIT);
                }
                // If the ball hit the top right corner.
            } else if (upLine.isPointOnTheLine(hitPoint) && rightLine.isPointOnTheLine(hitPoint)) {
                if (trajectory.getM() == Double.POSITIVE_INFINITY) {
                    this.center = new Point(this.center.getX() + dx, this.center.getY() + dy - ALMOST_HIT);
                } else if (trajectory.getM() < 0) {
                    this.center = new Point(this.center.getX() + dx + ALMOST_HIT,
                            this.center.getY() + dy - ALMOST_HIT);
                } else if (trajectory.getM() == 0) {
                    this.center = new Point(this.center.getX() + dx + ALMOST_HIT, this.center.getY() + dy);
                    // If trajectory have a positive m - it could come from above the up line or right from rightLine.
                } else if (trajectory.getM() > 0 && trajectory.start().getY() < upLine.start().getY()) {
                    this.center = new Point(this.center.getX() + dx - ALMOST_HIT,
                            this.center.getY() + dy - ALMOST_HIT);
                } else if (trajectory.getM() > 0 && trajectory.start().getX() > rightLine.start().getX()) {
                    this.center = new Point(this.center.getX() + dx + ALMOST_HIT,
                            this.center.getY() + dy + ALMOST_HIT);
                }
                // If we hit the bottom left corner.
            } else if (downLine.isPointOnTheLine(hitPoint) && leftLine.isPointOnTheLine(hitPoint)) {
                if (trajectory.getM() == Double.POSITIVE_INFINITY) {
                    this.center = new Point(this.center.getX() + dx, this.center.getY() + dy + ALMOST_HIT);
                } else if (trajectory.getM() < 0) {
                    this.center = new Point(this.center.getX() + dx - ALMOST_HIT,
                            this.center.getY() + dy + ALMOST_HIT);
                } else if (trajectory.getM() == 0) {
                    this.center = new Point(this.center.getX() + dx - ALMOST_HIT, this.center.getY() + dy);
                    // If the trajectory have a positive m - it could come from down the up line or left from leftLine.
                } else if (trajectory.getM() > 0 && trajectory.start().getY() < downLine.start().getY()) {
                    this.center = new Point(this.center.getX() + dx - ALMOST_HIT,
                            this.center.getY() + dy - ALMOST_HIT);
                } else if (trajectory.getM() > 0 && trajectory.start().getX() > leftLine.start().getX()) {
                    this.center = new Point(this.center.getX() + dx + ALMOST_HIT,
                            this.center.getY() + dy + ALMOST_HIT);
                }
                // If we hit the bottom right corner.
            } else if (downLine.isPointOnTheLine(hitPoint) && rightLine.isPointOnTheLine(hitPoint)) {
                if (trajectory.getM() == Double.POSITIVE_INFINITY) {
                    this.center = new Point(this.center.getX() + dx, this.center.getY() + dy + ALMOST_HIT);
                } else if (trajectory.getM() > 0) {
                    this.center = new Point(this.center.getX() + dx + ALMOST_HIT,
                            this.center.getY() + dy + ALMOST_HIT);
                } else if (trajectory.getM() == 0) {
                    this.center = new Point(this.center.getX() + dx + ALMOST_HIT, this.center.getY() + dy);
                    // If the trajectory have a negative m - it could come from down the up line or left from leftLine.
                } else if (trajectory.getM() < 0 && trajectory.start().getY() < downLine.start().getY()) {
                    this.center = new Point(this.center.getX() + dx + ALMOST_HIT,
                            this.center.getY() + dy - ALMOST_HIT);
                } else if (trajectory.getM() < 0 && trajectory.start().getX() > rightLine.start().getX()) {
                    this.center = new Point(this.center.getX() + dx - ALMOST_HIT,
                            this.center.getY() + dy + ALMOST_HIT);
                }
                // If we hit the up edge.
            } else if (upLine.isPointOnTheLine(hitPoint)) {
                if (trajectory.getM() == Double.POSITIVE_INFINITY) {
                    this.center = new Point(this.center.getX() + dx, this.center.getY() + dy - ALMOST_HIT);
                } else if (trajectory.getM() > 0) {
                    this.center = new Point(this.center.getX() + dx - ALMOST_HIT,
                            this.center.getY() + dy - ALMOST_HIT);
                } else if (trajectory.getM() < 0) {
                    this.center = new Point(this.center.getX() + dx + ALMOST_HIT,
                            this.center.getY() + dy - ALMOST_HIT);
                }
                // If we hit the down line.
            } else if (downLine.isPointOnTheLine(hitPoint)) {
                if (trajectory.getM() == Double.POSITIVE_INFINITY) {
                    this.center = new Point(this.center.getX() + dx, this.center.getY() + dy + ALMOST_HIT);
                } else if (trajectory.getM() > 0) {
                    this.center = new Point(this.center.getX() + dx + ALMOST_HIT,
                            this.center.getY() + dy + ALMOST_HIT);
                } else if (trajectory.getM() < 0) {
                    this.center = new Point(this.center.getX() + dx - ALMOST_HIT,
                            this.center.getY() + dy + ALMOST_HIT);
                }
                // If we hit the left line.
            } else if (leftLine.isPointOnTheLine(hitPoint)) {
                if (trajectory.getM() == 0) {
                    this.center = new Point(this.center.getX() + dx - ALMOST_HIT, this.center.getY() + dy);
                } else if (trajectory.getM() > 0) {
                    this.center = new Point(this.center.getX() + dx - ALMOST_HIT,
                            this.center.getY() + dy - ALMOST_HIT);
                } else if (trajectory.getM() < 0) {
                    this.center = new Point(this.center.getX() + dx - ALMOST_HIT,
                            this.center.getY() + dy + ALMOST_HIT);
                }
                // If the ball hit the right line.
            } else if (rightLine.isPointOnTheLine(hitPoint)) {
                if (trajectory.getM() == 0) {
                    this.center = new Point(this.center.getX() + dx + ALMOST_HIT, this.center.getY() + dy);
                } else if (trajectory.getM() > 0) {
                    this.center = new Point(this.center.getX() + dx + ALMOST_HIT,
                            this.center.getY() + dy + ALMOST_HIT);
                } else if (trajectory.getM() < 0) {
                    this.center = new Point(this.center.getX() + dx + ALMOST_HIT,
                            this.center.getY() + dy - ALMOST_HIT);
                }
            }
            // We get the new velocity from the method hit (with the collidable object).
            Velocity newVelocity = collisionInfo.collisionObject().hit(this, hitPoint, this.getVelocity());
            // We set the new velocity to the ball after the hit.
            this.setVelocity(newVelocity);
            if (center.getX() < 5 || center.getX() > 795 || center.getY() > 595 || center.getY() < 5) {
                int i = 0;
            }
        }
    }


    /**
     * This method make the ball hold a game.GameEnvironment.
     *
     * @param environment - the environment.
     */
    public void holdGameEnvironment(GameEnvironment environment) {
        this.gameEnvironment = environment;
    }


    /**
     * This method is method from the interface sprites.Sprite - we need it to tell to the ball that the time
     * passed, right now what the ball do when the time passed is to move one step.
     */
    public void timePassed() {
        this.moveOneStep();

    }

    /**
     * This method add the ball to the game.Game game.
     *
     * @param gameLevel - game.Game game that we add to it the ball.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}