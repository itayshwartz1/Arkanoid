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
 * This class represent level3 of the game.
 */
public class Level3 implements LevelInformation {
    private final int numberOfBalls;
    private final int numberOfBlockToRemove;
    private final int paddleWidth;
    private final int paddleSpeed;
    private final String levelName;
    private final int paddleHeight;

    /**
     * This method is the constructor for level3.
     */
    public Level3() {
        this.numberOfBalls = 2;
        this.paddleHeight = 7;
        this.paddleSpeed = 4;
        this.paddleWidth = 150;
        this.numberOfBlockToRemove = 38;
        this.levelName = "Green 3";

    }

    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        for (int i = 0; i < this.numberOfBalls; i++) {
            list.add(Velocity.fromAngleAndSpeed(-45 + 90 * i, 4));
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
        return new Block(new Rectangle(new Point(0, 0), 800, 600),
                new Color(58, 121, 8, 255));
    }

    @Override
    public List<Block> blocks() {
        Color[] colors = {Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.WHITE};
        List<Block> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10 - i; j++) {
                list.add(new Block(new Rectangle(new Point(790 - (j + 1) * 52, 130 + 20 * i), 52, 20),
                        colors[i]));
            }
        }
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlockToRemove;
    }

    @Override
    public List<Sprite> getBackgroundObjects() {
        // We draw building with windows.
        ArrayList<Sprite> spritesObjects = new ArrayList<>();
        spritesObjects.add(new Block(new Rectangle(new Point(50, 400), 120, 300), Color.BLACK));
        spritesObjects.add(new Block(new Rectangle(new Point(95, 340), 30, 60), Color.DARK_GRAY));
        spritesObjects.add(new Block(new Rectangle(new Point(105, 180), 10, 160),
                new Color(83, 83, 83, 255)));
        spritesObjects.add(new Circle(new Point(110, 180), 11, new Color(187, 162, 66, 255),
                false));
        spritesObjects.add(new Circle(new Point(110, 180), 8, new Color(238, 65, 65, 255),
                false));
        spritesObjects.add(new Circle(new Point(110, 180), 5, Color.WHITE,
                false));
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                spritesObjects.add(new Block(new Rectangle(new Point(57 + 23 * j, 407 + 43 * i), 13,
                        35), Color.WHITE));
            }
        }
        return spritesObjects;
    }
}
