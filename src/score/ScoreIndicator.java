//318528171

package score;

import biuoop.DrawSurface;
import difference.Counter;
import game.GameLevel;
import sprites.Block;
import sprites.Sprite;

/**
 * @author Itay Shwartz
 * This class represent score.ScoreIndicator - kind of sprite that hold sprites.Block And counter.
 */
public class ScoreIndicator implements Sprite {
    private final Block block;
    private final Counter counterScore;

    /**
     * This is the constructor of score.ScoreIndicator.
     *
     * @param block        - the block.
     * @param counterScore - the counter.
     */
    public ScoreIndicator(Block block, Counter counterScore) {
        // We put the given sprites.Block amd difference.Counter in the field.
        this.block = block;
        this.counterScore = counterScore;
    }

    @Override
    public void drawOn(DrawSurface d) {
        // We draw the block and on it we draw the score.
        block.drawOn(d);
        d.drawText(370, 15, "Score: " + counterScore.getValue(), 15);
    }

    @Override
    public void timePassed() {
    }

    /**
     * This method add the score.ScoreIndicator to the game as sprite.
     *
     * @param gameLevel - the game that we added to.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}
