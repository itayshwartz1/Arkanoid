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
 * This class represent level3 of the game.
 */
public class Level4 implements LevelInformation {
    private final int numberOfBalls;
    private final int numberOfBlockToRemove;
    private final int paddleWidth;
    private final int paddleSpeed;
    private final String levelName;
    private final int paddleHeight;

    /**
     * @author Itay Shwartz
     * This class represent level4 of the game.
     */
    public Level4() {
        this.numberOfBalls = 3;
        this.paddleHeight = 7;
        this.paddleSpeed = 4;
        this.paddleWidth = 150;
        this.numberOfBlockToRemove = 95;
        this.levelName = "Final Four";

    }

    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        for (int i = 0; i < this.numberOfBalls; i++) {
            list.add(Velocity.fromAngleAndSpeed(-45 + 45 * i, 4));
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
                new Color(43, 147, 219, 255));
    }

    @Override
    public List<Block> blocks() {
        Color[] colors = {Color.GRAY, Color.RED, Color.YELLOW, Color.GREEN, Color.WHITE, Color.PINK, Color.cyan};
        List<Block> list = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 15; j++) {
                list.add(new Block(new Rectangle(new Point(790 - (j + 1) * 52, 80 + 20 * i), 52, 20),
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
        // We draw two clouds.
        ArrayList<Sprite> spritesObjects = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            spritesObjects.add(new Line(new Point(85 + 8 * i, 400), new Point(55 + 8 * i, 605),
                    Color.WHITE));
        }
        spritesObjects.add(new Circle(new Point(80, 400), 20, new Color(208, 207, 207, 255),
                false));
        spritesObjects.add(new Circle(new Point(98, 413), 23, new Color(208, 207, 207, 255),
                false));
        spritesObjects.add(new Circle(new Point(118, 394), 25, new Color(187, 186, 186, 255),
                false));
        spritesObjects.add(new Circle(new Point(123, 415), 17, new Color(180, 180, 180, 255),
                false));
        spritesObjects.add(new Circle(new Point(145, 400), 27, new Color(180, 180, 180, 255),
                false));

        for (int i = 0; i < 10; i++) {
            spritesObjects.add(new Line(new Point(600 + 8 * i, 500), new Point(570 + 8 * i, 605),
                    Color.WHITE));
        }
        spritesObjects.add(new Circle(new Point(590, 490), 20, new Color(208, 207, 207, 255),
                false));
        spritesObjects.add(new Circle(new Point(610, 510), 23, new Color(208, 207, 207, 255),
                false));
        spritesObjects.add(new Circle(new Point(625, 490), 25, new Color(187, 186, 186, 255),
                false));
        spritesObjects.add(new Circle(new Point(639, 509), 17, new Color(180, 180, 180, 255),
                false));
        spritesObjects.add(new Circle(new Point(665, 505), 27, new Color(180, 180, 180, 255),
                false));
        return spritesObjects;
    }
}
