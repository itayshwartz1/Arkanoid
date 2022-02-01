//318528171

package listeners;

import sprites.Ball;
import sprites.Block;

/**
 * @author Itay Shwartz
 * This interface is for all the object that are listing to hit.
 */
public interface HitListener {

    /**
     * his method is called whenever the beingHit object is hit. The hitter parameter is the sprites.Ball that's
     * doing the hitting.
     *
     * @param beingHit - the block that being hit.
     * @param hitter   - the ball that hit
     */
    void hitEvent(Block beingHit, Ball hitter);
}