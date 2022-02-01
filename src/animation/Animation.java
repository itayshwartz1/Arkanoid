//318518171

package animation;

import biuoop.DrawSurface;

/**
 * @author Itay Shwartz
 * This interface if for Animation.
 */
public interface Animation {
    /**
     * This method draw the Animation for one frame.
     *
     * @param d - the surface that we draw on.
     */
    void doOneFrame(DrawSurface d);

    /**
     * This method check if the animation drawing should stop.
     *
     * @return boolean - should stop or not
     */
    boolean shouldStop();
}