package rainbowreef.game.gameObjects.stationary;

import rainbowreef.game.gameObjects.Collidable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BreakableBrick extends Brick{

    int x, y, id, hit;
    //gonna use an id number to track bricks
    private Rectangle hitBox;
    private BufferedImage breakBrick;
    private boolean collision = false;


    public BreakableBrick(int x, int y, int id, int hit, BufferedImage breakBrick) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.hit = hit;
        this.breakBrick = breakBrick;
        this.hitBox = new Rectangle(x, y, this.breakBrick.getWidth(), this.breakBrick.getHeight());
    }

    @Override
    public void update() {

    }

    @Override
    public Rectangle getHitBox() {return this.hitBox.getBounds();}

    @Override
    public void collisionHandler(Collidable c) {

    }

    @Override
    public boolean collision() {return collision;}

    @Override
    public boolean isBreakable() {
        return true;
    }


    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int newId) {
        this.id = newId;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    @Override
    public int getX() {
        return x;
    }

    public int getWidth(){
        return this.breakBrick.getWidth();
    }

    @Override
    public void drawImage(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(this.breakBrick, x, y, null);

        g2.setColor(Color.RED);
        g2.drawRect(x, y, this.breakBrick.getWidth(), this.breakBrick.getHeight());
    }
}
