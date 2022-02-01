//318528171

package listeners;

/**
 * @author Itay Shwartz
 * This interface is for all the object that are notify about hit.
 */
public interface HitNotifier {

    /**
     * This method add hl as a listener to hit events.
     *
     * @param hl - the hit listener.
     */
    void addHitListener(HitListener hl);

    /**
     * This method remove hl from the list of listeners to hit events.
     *
     * @param hl - the hit listener.
     */
    void removeHitListener(HitListener hl);
}