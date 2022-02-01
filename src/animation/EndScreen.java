//318528171
package animation;

import biuoop.DrawSurface;
import difference.Counter;

import java.awt.Color;
import java.util.Random;

/**
 * @author Itay Shwartz
 * This class is the Animation for the end screen (win or lose).
 */
public class EndScreen implements Animation {
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;
    private static final int FONT_SIZE = 80;
    private static final int SMALL_FONT_SIZE = 25;
    private final Boolean isWin;
    private final Counter counter;
    private final Boolean stop = false;
    private float fontGetBigger = 0;

    /**
     * This method is the constructor for EndScreen.
     *
     * @param isWin   - Boolean that tell if we print the win or lose screen.
     * @param counter - the counter that tell us the score.
     */
    public EndScreen(Boolean isWin, Counter counter) {
        this.isWin = isWin;
        this.counter = counter;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.fontGetBigger > FONT_SIZE) {
            this.fontGetBigger = FONT_SIZE;
        }
        fontGetBigger++;
        // We draw the background.
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        // if isWin is true - we print nicely win screen.
        int backgroundCircles = 100;
        if (isWin) {
            for (int i = 0; i < backgroundCircles; i++) {
                Random rand = new Random();
                int rand1 = rand.nextInt(SCREEN_WIDTH);
                int rand2 = rand.nextInt(SCREEN_HEIGHT);
                d.setColor(Color.CYAN);
                d.fillCircle(rand1, rand2, 1);
            }
            d.setColor(Color.GREEN);
            d.drawText(320 - (int) this.fontGetBigger, d.getHeight() / 2, "You Win!", (int) fontGetBigger);
            d.setColor(Color.CYAN);
            d.drawText(322 - (int) this.fontGetBigger, d.getHeight() / 2 + 2, "You Win!", (int) fontGetBigger);
            d.setColor(Color.GREEN);
            d.drawText(324 - (int) this.fontGetBigger, d.getHeight() / 2 + 4, "You Win!", (int) fontGetBigger);
        } else {
            d.setColor(Color.BLACK);
            d.fillRectangle(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
            d.setColor(Color.RED);
            d.drawText(190, d.getHeight() / 2, "Game Over", FONT_SIZE);
            d.setColor(Color.ORANGE);
            d.drawText(192, d.getHeight() / 2 + 2, "Game Over", FONT_SIZE);
            d.setColor(Color.RED);
            d.drawText(194, d.getHeight() / 2 + 4, "Game Over", FONT_SIZE);
        }
        d.drawText(310, d.getHeight() / 2 + 100, "Your score is: " + counter.getValue(), SMALL_FONT_SIZE);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}

