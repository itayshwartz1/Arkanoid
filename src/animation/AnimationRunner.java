//318528171
package animation;

import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * @author Itay Shwartz
 * This class run Animation.
 */
public class AnimationRunner {
    private final GUI gui;
    private final int framesPerSecond;

    /**
     * This is the constructor for AnimationRunner.
     *
     * @param gui - the GUI.
     */
    public AnimationRunner(GUI gui) {
        this.framesPerSecond = 1000 / 60;
        this.gui = gui;
    }

    /**
     * This method run the animation.
     *
     * @param animation - the Animation to run.
     */
    public void run(Animation animation) {
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        // We run the animation till we should stop
        while (!animation.shouldStop()) {
            // timing
            long startTime = System.currentTimeMillis();
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            // We check how much time we should wait till the next iteration on the loop.
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = this.framesPerSecond - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}