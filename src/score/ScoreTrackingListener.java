//318528171

package score;

import difference.Counter;
import listeners.HitListener;
import sprites.Ball;
import sprites.Block;

/**
 * @author Itay Shwartz
 * This class represent the listener score.ScoreTrackingListener that update the score for each hit.
 */
public class ScoreTrackingListener implements HitListener {
    private static final int SCORE_ADD_HIT = 5;
    private final Counter currentScore;

    /**
     * This method is the constructor of score.ScoreTrackingListener - put into the field currentScore the
     * given difference.Counter.
     *
     * @param scoreCounter - the difference.Counter that we get.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        // for every hit we add to the score SCORE_ADD_HIT points.
        this.currentScore.increase(SCORE_ADD_HIT);
    }
}