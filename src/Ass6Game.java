//318528171

import biuoop.GUI;
import biuoop.KeyboardSensor;
import animation.AnimationRunner;
import game.GameFlow;
import game.LevelInformation;
import levels.Level1;
import levels.Level2;
import levels.Level3;
import levels.Level4;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Itay Shwartz
 * This is the class that run the ass5 game.
 */
public class Ass6Game {
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;
    private static final int FIRST_LEVEL = 1;
    private static final int LAST_LEVEL = 4;

    /**
     * This is the main method that create new game, initialized and run it.
     *
     * @param args - the argument from the command line.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", SCREEN_WIDTH, SCREEN_HEIGHT);
        KeyboardSensor keyboardSensor = gui.getKeyboardSensor();
        GameFlow gameFlow = new GameFlow(new AnimationRunner(gui), keyboardSensor, gui);
        List<LevelInformation> basicsLevels = new ArrayList<>();
        basicsLevels.add(new Level1());
        basicsLevels.add(new Level2());
        basicsLevels.add(new Level3());
        basicsLevels.add(new Level4());
        // We run all the levels.
        List<LevelInformation> newLevels = new ArrayList<>();
        // We go through the args, and add the levels that required.
        for (String arg : args) {
            try {
                if (Integer.parseInt(arg) <= LAST_LEVEL && Integer.parseInt(arg) >= FIRST_LEVEL) {
                    newLevels.add(basicsLevels.get(Integer.parseInt(arg) - 1));
                }
            } catch (NumberFormatException ignored) {
            }
        }
        // If we didn't get valid level names from args we run the game flow with the 4 basic levels.
        if (newLevels.size() == 0) {
            gameFlow.runLevels(basicsLevels);
        } else {
            gameFlow.runLevels(newLevels);
        }
        gui.close();
    }
}
