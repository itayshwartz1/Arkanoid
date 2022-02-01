//318528171
package sprites;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Itay Shwartz
 * This class represent the sprite of the level name.
 */
public class LevelName implements Sprite {
    private final String text;

    /**
     * This is the constructor for LevelName.
     *
     * @param text - the name of the level.
     */
    public LevelName(String text) {
        this.text = text;
    }

    @Override
    public void drawOn(DrawSurface d) {
        // We draw the block and on it we draw the score.
        d.setColor(Color.BLACK);
        d.drawText(600, 15, "Level Name: " + this.text, 15);
    }

    @Override
    public void timePassed() {
    }
}
