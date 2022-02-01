//318528171
package animation;

import biuoop.DrawSurface;

/**
 * @author Itay Shwartz
 * This class is the puse screen Animation
 */
public class PauseScreen implements Animation {
    private final boolean stop;

    /**
     * This id the constructor for PauseScreen.
     */
    public PauseScreen() {
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        int startText = 170;
        int fontSize = 32;
        d.drawText(startText, d.getHeight() / 2, "paused -- press space to continue", fontSize);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
