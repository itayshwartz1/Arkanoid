//318528171

package listeners;

import difference.Counter;
import game.GameLevel;
import sprites.Ball;
import sprites.Block;

/**
 * @author Itay Shwartz
 * a listeners.BallRemover is in charge of removing balls from the game, as well as keeping count
 * of the number of balls that remain.
 */
public class BallRemover implements HitListener {
    private final GameLevel gameLevel;
    private final Counter remainingBalls;

    /**
     * This is the constructor for the class.
     *
     * @param gameLevel   - the game.
     * @param removedBall - count ant remember how much balls are exists in the game.
     */
    public BallRemover(GameLevel gameLevel, Counter removedBall) {
        this.gameLevel = gameLevel;
        this.remainingBalls = removedBall;
    }


    /**
     * If hit accrue this method remove the hitter ball from the game.
     *
     * @param beingHit - the sprites.Block.
     * @param hitter   - the sprites.Ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        // We remove the ball from the game, and decrease the amount of the balls by 1.
        gameLevel.removeSprite(hitter);
        remainingBalls.decreaseBy1();
    }
}