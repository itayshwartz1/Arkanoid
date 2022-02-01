//318528171

package game;

import animation.Animation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.KeyPressStoppableAnimation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collidable.Collidable;
import difference.Counter;
import geometry.Point;
import geometry.Rectangle;
import listeners.BallRemover;
import listeners.BlockRemover;
import score.ScoreIndicator;
import score.ScoreTrackingListener;
import animation.PauseScreen;
import sprites.Ball;
import sprites.Block;
import sprites.LevelName;
import sprites.Paddle;
import sprites.Sprite;
import sprites.SpriteCollection;

import java.awt.Color;

/**
 * @author Itay Shwartz
 * This class is for the game.Game object.
 */
public class GameLevel implements Animation {

    private static final Color EDGE_COLOR = Color.DARK_GRAY;
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;
    private static final int SCREEN_START_X = 0;
    private static final int SCREEN_START_Y = 0;
    private static final int BALL_RADIUS = 5;
    private static final int PADDLE_HEIGHT = 5;
    private static final Color BALL_COLOR = Color.white;
    private static final int EDGE_WIDTH = 10;
    private static final int SCORE_ADD_FOR_LEVEL_FINISH = 100;
    private static final int NUM_OF_SECONDS = 2;
    private static final int COUNT_FROM = 3;
    private static final String PAUSE_KEY = "p";

    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private final Counter blockCounter = new Counter();
    private final Counter ballCounter = new Counter();
    private final Counter scoreCounter;
    private AnimationRunner runner;
    private boolean running;
    private final KeyboardSensor keyboard;
    private final LevelInformation levelInformation;
    private final AnimationRunner an;

    /**
     * This method is the constructor of the object game.Game.
     *
     * @param levelInformation - information about the level.
     * @param ks               - KeyboardSensor.
     * @param an               - AnimationRunner.
     * @param counter          - Counter for the score.
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor ks, AnimationRunner an, Counter counter) {
        this.levelInformation = levelInformation;
        this.scoreCounter = counter;
        this.an = an;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.keyboard = ks;
    }

    /**
     * This method add a collidable. Collidable object to the game environment.
     *
     * @param c - collidable.Collidable object.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * This method add a sprites.Sprite object to the sprites.
     *
     * @param s - sprites.Sprite object.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * This method initialize a new game: create the Blocks and sprites.Ball (and sprites.Paddle)
     * and add them to the game.
     */
    public void initialize() {
        // We create all the listener.
        BlockRemover blockRemover = new BlockRemover(this, blockCounter);
        BallRemover ballRemover = new BallRemover(this, ballCounter);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(scoreCounter);

        // We add the sprite that we get from levelInformation to our SpriteCollection.
        sprites.addSprite(this.levelInformation.getBackground());
        for (Sprite sprite : this.levelInformation.getBackgroundObjects()) {
            sprites.addSprite(sprite);
        }
        Paddle paddle = new Paddle(keyboard, this.levelInformation.paddleWidth(), PADDLE_HEIGHT,
                this.levelInformation.paddleSpeed());
        paddle.addToGame(this);
        environment.setPaddle(paddle);
        // We create new balls.
        createBallsOnTopOfPaddle();
        // We create the edges blocks, and add them to the game.
        Block block1 = new Block(new Rectangle(new Point(SCREEN_START_X, SCREEN_START_Y + 2 * EDGE_WIDTH),
                SCREEN_WIDTH, EDGE_WIDTH), EDGE_COLOR);
        block1.addToGame(this);
        Block block2 = new Block(new Rectangle(new Point(SCREEN_START_X, SCREEN_START_Y), EDGE_WIDTH,
                2 * SCREEN_HEIGHT), EDGE_COLOR);
        block2.addToGame(this);
        Block block3 = new Block(new Rectangle(new Point(SCREEN_WIDTH - EDGE_WIDTH, SCREEN_START_X), EDGE_WIDTH,
                2 * SCREEN_HEIGHT), EDGE_COLOR);
        block3.addToGame(this);
        // the killing ball block located slightly under the screen.
        Block killBlock = new Block(new Rectangle(new Point(SCREEN_START_X, SCREEN_HEIGHT + EDGE_WIDTH), SCREEN_WIDTH,
                EDGE_WIDTH), EDGE_COLOR);
        killBlock.addToGame(this);
        // This block hold the listener ballRemover.
        killBlock.addHitListener(ballRemover);
        // We create scoreIndicator that located on the top of the screen.
        ScoreIndicator scoreIndicator = new ScoreIndicator(new Block(new Rectangle(new Point(SCREEN_START_X,
                SCREEN_START_Y), SCREEN_WIDTH, 2 * EDGE_WIDTH), Color.GRAY), scoreCounter);
        // We added the scoreIndicator to the game - when we go throw the sprites we draw it on the screen with score.
        scoreIndicator.addToGame(this);
        // We create all the game block according the instructions, and add them to the game.
        for (Block block : this.levelInformation.blocks()) {
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrackingListener);
            // for each block we add 1 to the blockCounter.
            blockCounter.increaseBy1();
        }
        LevelName levelName = new LevelName(this.levelInformation.levelName());
        this.sprites.addSprite(levelName);
    }

    /**
     * This method run the game - start the animation loop.
     */
    public void run() {
        this.runner = this.an;
        // countdown before turn starts.
        this.runner.run(new CountdownAnimation(NUM_OF_SECONDS, COUNT_FROM, sprites));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of the game.
        this.runner.run(this);
    }

    /**
     * This method create balls on top of the paddle.
     */
    private void createBallsOnTopOfPaddle() {
        int topOfPaddle = 20;
        int breadthBetweenBalls = 2;
        // The number of balls we create is the lenght of the numberOfBalls that we get from levelInformation.
        for (int i = 0; i < this.levelInformation.numberOfBalls(); i++) {
            Ball ball = new Ball(SCREEN_WIDTH / 2 + breadthBetweenBalls * i, SCREEN_HEIGHT - topOfPaddle,
                    BALL_RADIUS, BALL_COLOR);
            ball.setVelocity(this.levelInformation.initialBallVelocities().get(i));
            ball.addToGame(this);
            ball.holdGameEnvironment(environment);
            ballCounter.increase(1);
        }
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // We notify to all the sprites that the time passed.
        this.sprites.notifyAllTimePassed();
        // We draw all the sprites in the game.
        this.sprites.drawAllOn(d);

        // if we finished to remove all the block, we add 100 points to the the score and return.
        if (this.blockCounter.getValue() <= 0) {
            this.scoreCounter.increase(SCORE_ADD_FOR_LEVEL_FINISH);
            this.running = false;
        }
        // if there is no ,ore balls, we return.
        if (this.ballCounter.getValue() <= 0) {
            this.running = false;
        }
        if (this.keyboard.isPressed(PAUSE_KEY)) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, "space", new PauseScreen()));
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * This method remove collidable from the environment of the game.
     *
     * @param c - the collidable.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * This method remove sprite from the sprites of the game.
     *
     * @param s - the sprite.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * This method return the numbers of balls that remain on the screen from the ballCounter.
     *
     * @return int
     */
    public int ballsRemain() {
        return this.ballCounter.getValue();
    }

    /**
     * This method return the numbers of blocks that remain on the screen from the blockCounter.
     *
     * @return int
     */
    public int blockRemain() {
        return this.blockCounter.getValue();
    }
}