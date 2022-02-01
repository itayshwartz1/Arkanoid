//318528171

package sprites;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collidable.Collidable;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;

import java.awt.Color;


/**
 * @author Itay Shwartz
 * This class is the class for the object sprites.Paddle.
 */
public class Paddle implements Sprite, Collidable {
    private final biuoop.KeyboardSensor keyboard;
    private final Color paddleColor;
    private final int paddleHeight;
    private final int paddleWidth;
    private final int paddleSpeed;
    private Block block;
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;
    private static final int EDGE_BLOCK_HEIGHT = 10;
    private static final int TWO = 2;
    private static final int MAX_LEFT_POINT = EDGE_BLOCK_HEIGHT;
    private static final int MAX_RIGHT_POINT = SCREEN_WIDTH - EDGE_BLOCK_HEIGHT;
    private static final int DEGREES_AFTER_HIT_AREA_ONE = -60;
    private static final int DEGREES_AFTER_HIT_AREA_TWO = -30;
    private static final int DEGREES_AFTER_HIT_AREA_FOUR = 30;
    private static final int DEGREES_AFTER_HIT_AREA_FIVE = 60;

    /**
     * This method is the constructor for the object sprites.Paddle.
     *
     * @param keyboardSensor -KeyboardSensor.
     * @param paddleWidth    - the paddle width.
     * @param paddleHeight   - the paddle height.
     * @param paddleSpeed    - the paddle speed.
     */
    public Paddle(KeyboardSensor keyboardSensor, int paddleWidth, int paddleHeight, int paddleSpeed) {
        this.keyboard = keyboardSensor;
        this.paddleHeight = paddleHeight;
        this.paddleWidth = paddleWidth;
        this.paddleSpeed = paddleSpeed;
        this.paddleColor = Color.GRAY;
        // We set a new sprites.Block for the paddle.
        this.block = new Block(new Rectangle(new Point((SCREEN_WIDTH - this.paddleWidth) / 2,
                SCREEN_HEIGHT - EDGE_BLOCK_HEIGHT - this.paddleHeight), this.paddleWidth, this.paddleHeight),
                this.paddleColor);
    }

    /**
     * This method move tha paddle left if if it possible according our boundaries.
     */
    public void moveLeft() {
        // We take the x and y value of the upper left of the paddle.
        double x = this.block.getCollisionRectangle().getUpperLeft().getX();
        double y = this.block.getCollisionRectangle().getUpperLeft().getY();
        // if on the next step we will get out of the screen - we move the paddle to the max left point that we can.
        if (x - this.paddleSpeed <= MAX_LEFT_POINT) {
            this.block = new Block(new Rectangle(new Point(this.paddleSpeed, y), this.paddleWidth, this.paddleHeight),
                    this.paddleColor);
            // If we can move the paddle to the left - we set a new block to the paddle PADDLE_MOVE steps left.
        } else {
            this.block = new Block(new Rectangle(new Point(x - this.paddleSpeed, y),
                    this.paddleWidth, this.paddleHeight), this.paddleColor);
        }
    }


    /**
     * This method move tha paddle right if if it possible according our boundaries.
     */
    public void moveRight() {
        // We take the x and y value of the upper left of the paddle.
        double x = this.block.getCollisionRectangle().getUpperLeft().getX();
        double y = this.block.getCollisionRectangle().getUpperLeft().getY();
        // if on the next step we will get out of the screen - we move the paddle to the max right point that we can.
        if (x + this.paddleWidth + this.paddleSpeed >= MAX_RIGHT_POINT) {
            this.block = new Block(new Rectangle(new Point(MAX_RIGHT_POINT - this.paddleWidth, y),
                    this.paddleWidth, this.paddleHeight), Color.GRAY);
            // If we can move the paddle to the right - we set a new block to the paddle PADDLE_MOVE steps right.
        } else {
            this.block = new Block(new Rectangle(new Point(x + this.paddleSpeed, y),
                    this.paddleWidth, this.paddleHeight), this.paddleColor);
        }
    }

    /**
     * This method tell to the paddle that time passed. right now we mobe the paddle if the user press on the left
     * or right key.
     */
    public void timePassed() {
        // We do moveLeft or moveRight only if the user press on the left or right keys.
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }


    /**
     * This method draw the paddle on a given surface.
     *
     * @param d - the given surface.
     */
    public void drawOn(DrawSurface d) {
        this.block.drawOn(d);
    }


    /**
     * This method return back the shape of the paddle - the rectangle that build the paddle.
     *
     * @return - geometry.Rectangle of the paddle.
     */
    public Rectangle getCollisionRectangle() {
        return this.block.getCollisionRectangle();
    }


    /**
     * This method get a collition point and the current velocity of the ball and according where the collition
     * point hit the paddle we change the velocity to a new velocity. and return the new velocity.
     *
     * @param hitter          - the ball.
     * @param collisionPoint  - the point that the collide occurred.
     * @param currentVelocity - the current velocity of the collide object.
     * @return the new velocity after the hit.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // If there is a hit:
        if (this.block.getCollisionRectangle().getUpLine().isPointOnTheLine(collisionPoint)) {
            // we get the speed and the angle of the ball.
            double speed = Math.sqrt(Math.pow(currentVelocity.getDx(), TWO) + Math.pow(currentVelocity.getDy(), TWO));
            double angle = Math.atan(currentVelocity.getDx() / currentVelocity.getDy());
            // If the ball hit in area 1 - we change the velocity according DEGREES_AFTER_HIT_AREA_ONE.
            if (collisionPoint.getX() < this.getCollisionRectangle().getUpperLeft().getX() + this.paddleWidth / 5) {
                return Velocity.fromAngleAndSpeed(DEGREES_AFTER_HIT_AREA_ONE, speed);
            }
            // If the ball hit in area 2 - we change the velocity according DEGREES_AFTER_HIT_AREA_TWO.
            if (collisionPoint.getX() < this.getCollisionRectangle().getUpperLeft().getX()
                    + 2 * this.paddleWidth / 5) {
                return Velocity.fromAngleAndSpeed(DEGREES_AFTER_HIT_AREA_TWO, speed);
            }
            // If the ball hit in area 2 - we change the DY IN THE velocity.
            if (collisionPoint.getX() <= this.getCollisionRectangle().getUpperLeft().getX()
                    + 3 * this.paddleWidth / 5) {
                return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            }
            // If the ball hit in area 4 - we change the velocity according DEGREES_AFTER_HIT_AREA_FOUR.
            if (collisionPoint.getX() <= this.getCollisionRectangle().getUpperLeft().getX()
                    + 4 * this.paddleWidth / 5) {
                return Velocity.fromAngleAndSpeed(DEGREES_AFTER_HIT_AREA_FOUR, speed);
            }
            // If the ball hit in area 5 - we change the velocity according DEGREES_AFTER_HIT_AREA_FIVE.
            if (collisionPoint.getX() <= this.getCollisionRectangle().getUpperLeft().getX()
                    + 5 * this.paddleWidth / 5) {
                return Velocity.fromAngleAndSpeed(DEGREES_AFTER_HIT_AREA_FIVE, speed);
            }
        }

        /*
         If we get here then the collide point is not on the top of paddle - the hit is on one of the sides - then
         we want to change only the dx value to -dx:
         */
        return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
    }


    /**
     * This method add the paddle to the game.
     *
     * @param g - the game that we add to it the paddle.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }


    /**
     * This method return the paddle width.
     *
     * @return - int, the width.
     */
    public int getPaddleWidth() {
        return this.paddleWidth;
    }


    /**
     * This method return the paddle height.
     *
     * @return - int, the height.
     */
    public int getPaddleHeight() {
        return this.paddleHeight;
    }
}