package rainbowreef.game.gameObjects.stationary;

import rainbowreef.game.gameObjects.Collidable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Border extends Brick {

    int x, y;
    private Rectangle hitBox;
    private BufferedImage border;

    public Border(int x, int y, BufferedImage border) {
        this.x = x;
        this.y = y;
        this.border = border;
        this.hitBox = new Rectangle(x, y, border.getWidth(), border.getHeight());
    }

    @Override
    public void update(){}

    @Override
    public Rectangle getHitBox() {return this.hitBox.getBounds(); }

    @Override
    public void collisionHandler(Collidable c) {

    }

    @Override
    public boolean collision(){return false;}

    @Override
    public boolean isBreakable() {
        return false;
    }


    @Override
    public int getId() {
        return 1;
    }

    @Override
    public void setId(int id) {

    }

    @Override
    public int getHit() {
        return 0;
    }

    @Override
    public void setHit(int hit) {

    }

    @Override
    public void drawImage(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(this.border, x, y, null);
    }
}
