//318528171

package collidable;

import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import sprites.Ball;

/**
 * @author Itay Shwartz
 * This interface is for all the abject that are can be collidable with them.
 */
public interface Collidable {

    /**
     * This method return the "collision shape" of the object.
     *
     * @return geometry.Rectangle.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     *
     * @param hitter          - the ball.
     * @param collisionPoint  - the point that the collide occurred.
     * @param currentVelocity - the current velocity of the collide object.
     * @return geometry.Velocity - new velocity expected after the hit (based on the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}