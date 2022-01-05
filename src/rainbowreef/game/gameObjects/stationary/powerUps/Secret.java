package rainbowreef.game.gameObjects.stationary.powerUps;

import rainbowreef.game.gameObjects.Collidable;
import rainbowreef.game.gameObjects.stationary.Brick;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Secret extends Brick {
    private int x, y, hit;
    private Rectangle hitBox;
    private BufferedImage secret;
    private boolean collision = false;
    private int id;


    public Secret(int x, int y, int id, int hit, BufferedImage secret) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.hit = hit;
        this.secret = secret;
        this.hitBox = new Rectangle(x, y, this.secret.getWidth(), this.secret.getHeight());
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

    @Override
    public int getHit() {
        return this.hit;
    }

    @Override
    public void setHit(int hit) {
        this.hit = hit;
    }

    @Override
    public int getX() {
        return x;
    }

    public int getWidth(){
        return this.secret.getWidth();
    }


    @Override
    public void drawImage(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(this.secret, x, y, null);
    }
}
