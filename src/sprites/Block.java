//318528171

package sprites;

import biuoop.DrawSurface;
import collidable.Collidable;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import listeners.HitListener;
import listeners.HitNotifier;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Itay Shwartz
 * This is the Class for sprites.Block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private final Rectangle rectangle;
    private final Color blockColor;
    private final List<HitListener> hitListeners;

    /**
     * This is the constructor for the sprites.Block.
     *
     * @param rectangle - the shape that we create from a block.
     * @param color     - the color of the block
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.blockColor = color;
        // We create a list that hold all the listener to this block.
        this.hitListeners = new ArrayList<>();
    }

    /**
     * This method return the "collision shape" of the sprites.Block.
     *
     * @return - the rectangle of the block.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * This method notify to all the listener that hit accrued.
     *
     * @param hitter - the ball.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * This method notify the object that we collided with it at collisionPoint with a given velocity.
     *
     * @param hitter          - the ball.
     * @param collisionPoint  - the collision point with the block.
     * @param currentVelocity - the current velocity of the collided object.
     * @return - is the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // First we extract the lines from the rectangle and the velocity dx dy for comfort.
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        Line upLine = this.rectangle.getUpLine();
        Line downLine = this.rectangle.getDownLine();
        Line leftLine = this.rectangle.getLeftLine();
        Line rightLine = this.rectangle.getRightLine();
        // After we take all the data from the block we can now notify that hit accrued.
        this.notifyHit(hitter);

        /*
        Now we check where the point hit and according that we return new velocity.
        if the hit is on the peak - the collisionPoint is on two lines.
         */
        if (upLine.isPointOnTheLine(collisionPoint) && leftLine.isPointOnTheLine(collisionPoint)) {
            if (dx < 0 && dy > 0) {
                return new Velocity(dx, -dy);
            }
            if (dx == 0) {
                return new Velocity(dx, -dy);
            }
            if (dx > 0 && dy > 0) {
                return new Velocity(-dx, -dy);
            }
            if (dy == 0) {
                return new Velocity(-dx, dy);
            }
            if (dx > 0 && dy < 0) {
                return new Velocity(-dx, dy);
            }
        }
        if (upLine.isPointOnTheLine(collisionPoint) && rightLine.isPointOnTheLine(collisionPoint)) {
            if (dx > 0 && dy > 0) {
                return new Velocity(dx, -dy);
            }
            if (dx == 0) {
                return new Velocity(dx, -dy);
            }
            if (dx < 0 && dy > 0) {
                return new Velocity(-dx, -dy);
            }
            if (dy == 0) {
                return new Velocity(-dx, dy);
            }
            if (dx < 0 && dy < 0) {
                return new Velocity(-dx, dy);
            }
        }
        if (leftLine.isPointOnTheLine(collisionPoint) && downLine.isPointOnTheLine(collisionPoint)) {
            if (dx > 0 && dy > 0) {
                return new Velocity(-dx, dy);
            }
            if (dx == 0) {
                return new Velocity(dx, -dy);
            }
            if (dx > 0 && dy < 0) {
                return new Velocity(-dx, -dy);
            }
            if (dy == 0) {
                return new Velocity(-dx, dy);
            }
            if (dx < 0 && dy < 0) {
                return new Velocity(-dx, dy);
            }
        }
        if (downLine.isPointOnTheLine(collisionPoint) && rightLine.isPointOnTheLine(collisionPoint)) {
            if (dx > 0 && dy < 0) {
                return new Velocity(dx, -dy);
            }
            if (dx == 0) {
                return new Velocity(dx, -dy);
            }
            if (dx < 0 && dy < 0) {
                return new Velocity(-dx, -dy);
            }
            if (dy == 0) {
                return new Velocity(-dx, dy);
            }
            if (dx < 0 && dy > 0) {
                return new Velocity(-dx, dy);
            }
        }

        // If the hit is only on one line and not on the peak.
        if (upLine.isPointOnTheLine(collisionPoint)) {
            return new Velocity(dx, -dy);
        }
        if (downLine.isPointOnTheLine(collisionPoint)) {
            return new Velocity(dx, -dy);
        }
        if (leftLine.isPointOnTheLine(collisionPoint)) {
            return new Velocity(-dx, dy);
        }
        if (rightLine.isPointOnTheLine(collisionPoint)) {
            return new Velocity(-dx, dy);
        }

        /*
         If there is no hit (maybe we start this method by mistake ) then the point is not on any line we return
         the current velocity.
         */
        return currentVelocity;
    }


    /**
     * This method draw a block on a given surface.
     *
     * @param surface - the surface that we draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.blockColor);
        surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    /**
     * This method tell to the block that time passed. right now we do nothing with that information. later we do.
     */
    public void timePassed() {
    }


    /**
     * This method add the block to a given game.
     *
     * @param gameLevel - game.Game game object that we add to it the block.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addCollidable(this);
        gameLevel.addSprite(this);
    }

    /**
     * This method remove this block from the game.
     *
     * @param gameLevel - the game.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
