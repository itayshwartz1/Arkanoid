//318528171

package difference;

/**
 * @author Itay Shwartz
 * This Class represent counter that can remember the count.
 */
public class Counter {
    private int currentNum;

    /**
     * This method is the constructor for difference.Counter.
     */
    public Counter() {
        this.currentNum = 0;
    }

    /**
     * add number to current count.
     *
     * @param number - the number that we add to the current count
     */
    public void increase(int number) {
        this.currentNum += number;
    }

    /**
     * This method increase the current count by one.
     */
    public void increaseBy1() {
        increase(1);
    }

    /**
     * This method subtract number from current count.
     *
     * @param number - the number that we subtract
     */
    public void decrease(int number) {
        this.currentNum -= number;
    }

    /**
     * This method decrease the the current count by one.
     */
    public void decreaseBy1() {
        decrease(1);
    }

    /**
     * This method get current count.
     *
     * @return int
     */
    public int getValue() {
        return this.currentNum;
    }
}