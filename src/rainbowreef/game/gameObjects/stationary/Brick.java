package rainbowreef.game.gameObjects.stationary;

import rainbowreef.game.gameObjects.Collidable;
import rainbowreef.game.gameObjects.GameObjects;

import java.awt.*;

public abstract class Brick extends GameObjects implements Collidable {

    public Brick (){};

    public abstract void drawImage(Graphics g);

    public abstract void update();

    public abstract boolean collision();

    public abstract boolean isBreakable();

    public abstract int getId();

    public abstract void setId(int id);

    public abstract int getHit();

    public abstract void setHit(int hit);

}
