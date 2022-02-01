//318528171
package animation;

import biuoop.DrawSurface;
import sprites.SpriteCollection;

import java.awt.Color;

/**
 * @author Itay Shwartz
 * This class is the Animation that count down on the screen.
 */
public class CountdownAnimation implements Animation {

    private final double numOfSeconds;
    private final int countFrom;
    private final SpriteCollection gameScreen;
    private int framePast = 0;

    /**
     * This method is the constructor for CountdownAnimation.
     *
     * @param numOfSeconds - the num of second that we wait till we finished to count down
     * @param countFrom    - the number that we start to print in the count down
     * @param gameScreen   - the Sprite that we draw on the screen behind the count down
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // We draw all the sprites on the surface.
        gameScreen.drawAllOn(d);
        // The number of frames that enter by a difference of two numbers in the countdown.
        double frameInSecond = (this.numOfSeconds / this.countFrom) * 60;
        int fontSize = 50;
        // We print the countdown nicely so we print the printing number of times.
        d.setColor(new Color(81, 1, 111));
        d.drawText(386, 301, String.valueOf(countFrom - (int) (framePast / frameInSecond)), fontSize);
        d.setColor(new Color(50, 150, 153));
        d.drawText(387, 302, String.valueOf(countFrom - (int) (framePast / frameInSecond)), fontSize);
        d.setColor(new Color(81, 1, 111));
        d.drawText(388, 303, String.valueOf(countFrom - (int) (framePast / frameInSecond)), fontSize);
        // for each iteration we raise the framePast.
        framePast++;
    }

    @Override
    public boolean shouldStop() {
        int framePerSecond = 60;
        // If we run more frames then the time of the count down.
        if (this.numOfSeconds <= framePast / framePerSecond) {
            framePast = 0;
            return true;
        }
        return false;
    }
}