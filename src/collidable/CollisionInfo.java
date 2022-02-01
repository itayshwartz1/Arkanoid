//318528171

package collidable;

import geometry.Point;

/**
 * @author Itay Shwartz
 * This is the Class for collidable.CollisionInfo object.
 */
public class CollisionInfo {
    private final Collidable collidableObject;
    private final Point collitionPoint;

    /**
     * This is the constructor for collidable.CollisionInfo.
     *
     * @param collidableObject - collidable.Collidable.
     * @param collitionPoint   - geometry.Point.
     */
    public CollisionInfo(Collidable collidableObject, Point collitionPoint) {
        this.collidableObject = collidableObject;
        this.collitionPoint = collitionPoint;
    }

    /**
     * This method return the point at which the collision occurs.
     *
     * @return - geometry.Point.
     */
    public Point collisionPoint() {
        return this.collitionPoint;
    }

    /**
     * This method return the collidable object involved in the collision.
     *
     * @return collidable.Collidable.
     */
    public Collidable collisionObject() {
        return this.collidableObject;
    }
}