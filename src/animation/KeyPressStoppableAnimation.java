//318528171
package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Itay Shwartz
 * This class is decorator KeyPressStoppableAnimation that can stop the running with given key.
 */
public class KeyPressStoppableAnimation implements Animation {
    private boolean stop;
    private final KeyboardSensor sensor;
    private final String key;
    private final Animation animation;
    private boolean isAlreadyPressed = true;

    /**
     * This method is the constructor for KeyPressStoppableAnimation.
     *
     * @param sensor    - the KeyboardSensor
     * @param key       - the key that sign us to stop the running
     * @param animation - the Animation that we will run
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.sensor.isPressed(this.key)) {
            // If the sensor is already press we continue.
            if (this.isAlreadyPressed) {
                return;
            }
            this.stop = true;
        }
        this.isAlreadyPressed = false;
        animation.doOneFrame(d);
    }

    @Override
    public boolean shouldStop() {
        return stop;
    }

}