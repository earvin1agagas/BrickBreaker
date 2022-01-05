package rainbowreef.game.gameObjects.moveable;

import rainbowreef.game.gameObjects.Collidable;
import rainbowreef.game.gameObjects.GameObjects;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Enemy extends GameObjects implements Collidable {
    private int x, y, vx, hit;
    private Rectangle hitBox;
    private BufferedImage enemyImg;
    private boolean collision = false;
    private int score = 0;
    private int value;
    private int id;
    private final int ENEMY_SPEED = 1;

    public Enemy(int x, int y, int id, int hit, BufferedImage enemyImg) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.id = id;
        this.hit = hit;
        this.enemyImg = enemyImg;
        this.value = 100;
        this.hitBox = new Rectangle(x, y, this.enemyImg.getWidth(), this.enemyImg.getHeight());
    }

    public void movement() {

        checkBorder();
        this.hitBox.setLocation(x, y);
    }

    public void checkBorder() {
        if(x > x + 10) {
            x = x + 10;
        }
        if (x < x - 10) {
            x = x - 10;
        }
    }

    @Override
    public void update() {
        movement();
    }

    @Override
    public Rectangle getHitBox() {return this.hitBox.getBounds();}

    @Override
    public void collisionHandler(Collidable c) {

    }


    @Override
    public int getX() {
        return x;
    }


    @Override
    public int getWidth(){
        return this.enemyImg.getWidth();
    }

    @Override
    public int getY(){return y;}

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.x = y;
    }


    @Override
    public void drawImage(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(this.enemyImg, x, y, null);

        g2.setColor(Color.GREEN);
        g2.drawRect(x, y, this.enemyImg.getWidth(), this.enemyImg.getHeight());
    }
}
