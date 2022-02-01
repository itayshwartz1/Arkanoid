// 318528171

package sprites;

import java.util.ArrayList;

import biuoop.DrawSurface;

/**
 * This is the class for the colloction of sprites - sprites.SpriteCollection.
 */
public class SpriteCollection {
    private final ArrayList<Sprite> spritesObjects;

    /**
     * This method is the constructor for sprites.SpriteCollection. it create newArrayList for sprites.
     */
    public SpriteCollection() {
        this.spritesObjects = new ArrayList<>();
    }


    /**
     * This method add a new sprite to the sprites arrayList.
     *
     * @param s - the new sprite.
     */
    public void addSprite(Sprite s) {
        this.spritesObjects.add(s);
    }

    /**
     * This method remove a sprite from the sprites arrayList.
     *
     * @param s - the remove sprite.
     */
    public void removeSprite(Sprite s) {
        this.spritesObjects.remove(s);
    }

    /**
     * This method call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < spritesObjects.size(); i++) {
            spritesObjects.get(i).timePassed();
        }
    }

    /**
     * This method draw all the sprites on a given surface.
     *
     * @param d - the given surface.
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < spritesObjects.size(); i++) {
            spritesObjects.get(i).drawOn(d);
        }
    }
}