//318528171
package levels;

import game.LevelInformation;
import geometry.Circle;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import sprites.Block;
import sprites.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Itay Shwartz
 * This class represent level1 of the game.
 */
public class Level1 implements LevelInformation {
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;
    private final int numberOfBalls;
    private final int paddleHeight;
    private final int paddleSpeed;
    private final int paddleWidth;
    private final String levelName;
    private final int numberOfBlockToRemove;

    /**
     * This method is the constructor for level1.
     */
    public Level1() {
        this.numberOfBalls = 1;
        this.paddleSpeed = 4;
        this.paddleWidth = 150;
        this.numberOfBlockToRemove = 1;
        this.levelName = "Direct Hit";
        this.paddleHeight = 4;

    }

    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        int ballSpeedX = 0;
        int ballSpeedY = -4;
        List<Velocity> list = new ArrayList<>();
        list.add(new Velocity(ballSpeedX, ballSpeedY));
        return list;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    public String levelName() {
        return this.levelName;
    }

    @Override
    public Sprite getBackground() {
        return new Block(new Rectangle(new Point(0, 0), SCREEN_WIDTH, SCREEN_HEIGHT), Color.BLACK);

    }

    @Override
    public List<Block> blocks() {
        int blockWidth = 50;
        int blockHeight = 50;
        List<Block> list = new ArrayList<>();
        list.add(new Block(new Rectangle(new Point(375, 150), blockWidth, blockHeight), Color.RED));
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlockToRemove;
    }

    @Override
    public List<Sprite> getBackgroundObjects() {
        // We draw bullzeye
        ArrayList<Sprite> spritesObjects = new ArrayList<>();
        Circle circle1 = new Circle(new Point(400, 175), 75, Color.BLUE, true);
        Circle circle2 = new Circle(new Point(400, 175), 125, Color.BLUE, true);
        Circle circle3 = new Circle(new Point(400, 175), 175, Color.BLUE, true);
        spritesObjects.add((circle1));
        spritesObjects.add((circle2));
        spritesObjects.add((circle3));
        Block block1 = new Block(new Rectangle(new Point(200, 175), 150, 2), Color.BLUE);
        Block block2 = new Block(new Rectangle(new Point(450, 175), 150, 2), Color.BLUE);
        Block block3 = new Block(new Rectangle(new Point(399, -25), 2, 150), Color.BLUE);
        Block block4 = new Block(new Rectangle(new Point(399, 225), 2, 150), Color.BLUE);
        spritesObjects.add((block1));
        spritesObjects.add((block2));
        spritesObjects.add((block3));
        spritesObjects.add((block4));
        return spritesObjects;
    }
}
