//318528171
package game;

import animation.AnimationRunner;
import animation.KeyPressStoppableAnimation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import difference.Counter;
import animation.EndScreen;

import java.util.List;

/**
 * @author Itay Shwartz
 * This class is the class thar menege the game flow.
 */
public class GameFlow {
    private final AnimationRunner ar;
    private final KeyboardSensor ks;
    private final GUI gui;

    /**
     * This method is the constructor for GameFlow.
     *
     * @param ar  - the animation that we run.
     * @param ks  - the KeyboardSensor.
     * @param gui - the screen.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.ar = ar;
        this.ks = ks;
        this.gui = gui;
    }

    /**
     * This method run list of levels that passed to it.
     *
     * @param levels - list of the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        // We set new counter for the score here - that all the levels will know it.
        Counter counter = new Counter();
        // isWin is to flag to the EndScreen if we win or lose.
        boolean isWin = true;
        // We go through all the levels, initialized and run them.
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.ks, this.ar, counter);
            level.initialize();
            // We stop to run the level if there is no balls ar blocks remain.
            while (level.ballsRemain() > 0 && level.blockRemain() > 0) {
                level.run();
            }
            // If there is no balls remain we stop to run all the next levels and change isWin to false.
            if (level.ballsRemain() <= 0) {
                isWin = false;
                break;
            }
        }
        // We run win or lose screen, according isWin.
        new AnimationRunner(this.gui).run(new KeyPressStoppableAnimation(this.ks, "space",
                new EndScreen(isWin, counter)));
    }
}