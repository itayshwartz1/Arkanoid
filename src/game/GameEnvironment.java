//318528171

package game;

import collidable.Collidable;
import collidable.CollisionInfo;
import geometry.Line;
import geometry.Point;
import sprites.Paddle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Itay Shwartz
 * This is the Class for the object game.GameEnvironment.
 */
public class GameEnvironment {
    private final List<Collidable> collidableObjects;
    private static final int NO = 0;
    private static final int YES = 1;
    private Paddle paddle;


    /**
     * This is the constructor for game.GameEnvironment.
     */
    public GameEnvironment() {
        this.collidableObjects = new ArrayList<>();
    }


    /**
     * This method add a given collidable to the environment.
     *
     * @param c - collidable that we add.
     */
    public void addCollidable(Collidable c) {
        this.collidableObjects.add(c);
    }

    /**
     * This method remove a given collidable from the environment.
     *
     * @param c - collidable that we remove.
     */
    public void removeCollidable(Collidable c) {
        this.collidableObjects.remove(c);
    }


    /**
     * This method assume an object moving from line.start() to line.end() If this object will not collide with any
     * of the collidable in this collection, return null. Else, return the information about the closest collision
     * that is going to occur (by collidable.CollisionInfo).
     *
     * @param trajectory - the trajectory of the object.
     * @return - collidable.CollisionInfo / null.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point closestIntersectionPoint = null;
        Collidable closestCollidableIntersect = null;
        int weFoundCollisionObject = 0;

        /*
         We go through all the object in the environment. if we find a hit point from the trajectory to object  - we
         check if he the closest to the start of the trajectory. if he the closest, we set the collision point to be
         closestIntersectionPoint and the hit object to be closestCollidableIntersect.
         */
        for (int i = 0; i < this.collidableObjects.size(); i++) {
            Point point = trajectory.closestIntersectionToStartOfLine(
                    this.collidableObjects.get(i).getCollisionRectangle());
            if (point != null) {

                /*
                if we didn't found before collision object - the point and the object we fount are automatically the
                closest point and object. and we change weFoundCollisionObject to YES.
                 */
                if (weFoundCollisionObject == NO) {
                    closestIntersectionPoint = point;
                    closestCollidableIntersect = this.collidableObjects.get(i);
                    weFoundCollisionObject = YES;

                /*
                 If we found before a hit point - we check which from them is the closest and we change or not change
                 closestIntersectionPoint and closestCollidableIntersect according that.
                 */
                } else if (trajectory.start().distance(point) < trajectory.start().distance(closestIntersectionPoint)) {
                    closestIntersectionPoint = point;
                    closestCollidableIntersect = this.collidableObjects.get(i);
                }
            }
        }
        // If we reach to the end and we didn't fount collision point with the trajectory - we return null.
        if (closestCollidableIntersect == null) {
            return null;
        } else {
            // We return the collidable.CollisionInfo to the closestCollidableIntersect and closestIntersectionPoint.
            return new CollisionInfo(closestCollidableIntersect, closestIntersectionPoint);
        }
    }


    /**
     * This method set to the field paddle the given paddle.
     *
     * @param p - the paddle.
     */
    public void setPaddle(Paddle p) {
        this.paddle = p;
    }


    /**
     * This method get return the field paddle.
     *
     * @return - paddle.
     */
    public Paddle getPaddle() {
        return this.paddle;
    }
}