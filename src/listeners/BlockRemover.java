//318528171

package listeners;

import difference.Counter;
import game.GameLevel;
import sprites.Ball;
import sprites.Block;

/**
 * @author Itay Shwartz
 * a listeners.BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private final GameLevel gameLevel;
    private final Counter remainingBlocks;

    /**
     * This is the constructor for listeners.BlockRemover.
     *
     * @param gameLevel     - the game that we get.
     * @param removedBlocks - the counter that we get
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        // We remove the sprites.Block from the game and decrease the amount of blocks in the counter by 1.
        gameLevel.removeSprite(beingHit);
        gameLevel.removeCollidable(beingHit);
        remainingBlocks.decreaseBy1();
        beingHit.removeHitListener(this);
    }
}