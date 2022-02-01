//318528171
package game;

import geometry.Velocity;
import sprites.Block;
import sprites.Sprite;

import java.util.List;

/**
 * @author Itay Shwartz
 * This interfacr is for LevelInformation.
 */
public interface LevelInformation {

    /**
     * This method return the numbers of balls that remain.
     *
     * @return - int
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     *
     * @return - list
     */
    List<Velocity> initialBallVelocities();

    /**
     * This method return the paddle speed.
     *
     * @return - int
     */
    int paddleSpeed();

    /**
     * This method return the paddle width.
     *
     * @return - int
     */
    int paddleWidth();

    /**
     * This method return the level name.
     *
     * @return - string
     */
    String levelName();

    /**
     * This method returns a sprite with the background of the level.
     *
     * @return - Sprite
     */
    Sprite getBackground();

    /**
     * This method return the blocks that make up this level.
     *
     * @return - List
     */
    List<Block> blocks();

    /**
     * This method return the number of blocks that should be removed.
     *
     * @return int
     */
    int numberOfBlocksToRemove();

    /**
     * This method return the background objects.
     *
     * @return - List
     */
    List<Sprite> getBackgroundObjects();
}