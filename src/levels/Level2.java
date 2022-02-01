//318528171
package levels;

import game.LevelInformation;
import geometry.Circle;
import geometry.Line;
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
 * This class represent level2 of the game.
 */
public class Level2 implements LevelInformation {
    private final int numberOfBalls;
    private final int numberOfBlockToRemove;
    private final int paddleWidth;
    private final int paddleSpeed;
    private final String levelName;
    private final int paddleHeight;

    /**
     * This method is the constructor for level2.
     */
    public Level2() {
        this.numberOfBalls = 10;
        this.paddleHeight = 7;
        this.paddleSpeed = 4;
        this.paddleWidth = 600;
        this.numberOfBlockToRemove = 15;
        this.levelName = "Wide Easy";

    }
    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        for (int i = 0; i < this.numberOfBalls; i++) {
            list.add(Velocity.fromAngleAndSpeed(-45 + 9 * i, 4));
        }
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
        // We set the background.
        return new Block(new Rectangle(new Point(0, 0), 800, 600),
                new Color(218, 221, 255, 255));
    }

    @Override
    public List<Block> blocks() {
        Color[] colors = {Color.RED, Color.RED, Color.ORANGE, Color.ORANGE, Color.YELLOW, Color.YELLOW,
                Color.GREEN, Color.GREEN, Color.GREEN, Color.BLUE, Color.BLUE, Color.PINK, Color.PINK, Color.cyan,
                Color.cyan};
        List<Block> list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            list.add(new Block(new Rectangle(new Point(10 + 52 * i, 300), 52, 20),
                    colors[i % colors.length]));
        }
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlockToRemove;
    }

    @Override
    public List<Sprite> getBackgroundObjects() {
        // We draw sun with sun rays.
        ArrayList<Sprite> spritesObjects = new ArrayList<>();
        for (int i = 0; i < 70; i++) {
            spritesObjects.add(new Line(new Point(150, 150), new Point(10 * i, 300),
                    new Color(226, 189, 62, 255)));
        }
        spritesObjects.add(new Circle(new Point(150, 150), 80, new Color(173, 149, 72),
                false));
        spritesObjects.add(new Circle(new Point(150, 150), 70, new Color(219, 171, 47),
                false));
        spritesObjects.add(new Circle(new Point(150, 150), 60, new Color(248, 208, 60),
                false));
        return spritesObjects;
    }
}
