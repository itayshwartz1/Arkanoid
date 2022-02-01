//318528171

package sprites;

import biuoop.DrawSurface;


/**
 * @author Itay Shwartz
 * This is the interface for (sprite) game object.
 */
public interface Sprite {


    /**
     * This method draw the sprite to the screen.
     *
     * @param d - the given surface.
     */
    void drawOn(DrawSurface d);


    /**
     * This method notify to the sprite that the time passed.
     */
    void timePassed();
}