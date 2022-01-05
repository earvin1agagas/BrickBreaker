package rainbowreef.game.gameObjects.stationary;

import rainbowreef.game.gameObjects.Collidable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UnbreakableBrick extends Brick{

    int x, y;
    private Rectangle hitBox;
    private BufferedImage brickImg;
    private int id;

    public UnbreakableBrick(int x, int y, int id,BufferedImage brickImg) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.brickImg = brickImg;
        this.hitBox = new Rectangle(x, y, brickImg.getWidth(), brickImg.getHeight());
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
        return this.id;
    }

    @Override
    public void setId(int newId){
        this.id = this.id;
    }

    @Override
    public int getHit() {
        return 0;
    }

    @Override
    public void setHit(int hit) {

    }

    @Override
    public int getX() {
        return x;
    }

    public int getWidth(){
        return this.brickImg.getWidth();
    }

    @Override
    public void drawImage(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(this.brickImg, x, y, null);


        g2.setColor(Color.RED);
        g2.drawRect(x, y, this.brickImg.getWidth(), this.brickImg.getHeight());
    }
}
