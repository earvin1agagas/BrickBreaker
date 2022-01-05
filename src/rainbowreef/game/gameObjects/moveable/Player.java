package rainbowreef.game.gameObjects.moveable;

import rainbowreef.game.GameConstants;
import rainbowreef.game.gameObjects.Collidable;
import rainbowreef.game.gameObjects.GameObjects;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Player extends GameObjects implements Collidable {

    private int x, y;
    private float angle;
    private final int PLAYER_SPEED = 4;
    private final int R = 2;

    private BufferedImage img;
    private boolean LeftPressed = false;
    private boolean RightPressed = false;
    private boolean SpacePressed = false;
    private boolean pause = false;
    /**
     * Rectangle used for hitbox
     */
    private Rectangle hitBox;
    private int lives = 3;

    public Player(int x, int y, int angle, BufferedImage img) {
        this.x = x;
        this.y = y;
        this.img = img;
        this.angle = angle;
        this.hitBox = new Rectangle(x, y, this.img.getWidth(), this.img.getHeight()); //This will make hitbox size of player/paddle

    }

    @Override
    public Rectangle getHitBox() {return hitBox.getBounds();}

    //When keys are pressed
    void toggleLeftPressed() {this.LeftPressed = true;}
    void toggleRightPressed() {this.RightPressed = true;}
    void toggleSpacePressed() {this.SpacePressed = true;}

    //When keys are released
    void unToggleLeftPressed() {
        this.LeftPressed = false;
    }
    void unToggleRightPressed() {
        this.RightPressed = false;
    }
    void unToggleSpacePressed() {this.SpacePressed = false;}

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void update() {

        if(this.LeftPressed) {
            this.moveLeft();
        }

        if(this.RightPressed) {
            this.moveRight();
        }

        /**
         * Fix pause function if have time
         */

//        if(this.SpacePressed && Display.tickCount % 20 == 0) {
//            Display.setPause(true);
//            System.out.println("Pause button pressed");
//        }

    }


    private void moveLeft() {
        x -= PLAYER_SPEED;
        checkBorder();
        this.hitBox.setLocation(x, y);
    }

    private void moveRight() {
        x += PLAYER_SPEED;
        checkBorder();
        this.hitBox.setLocation(x, y);
    }



    private void checkBorder() {
        if(x < 30) {
            x = 30;
        }

        if(x >= GameConstants.GAME_SCREEN_WIDTH - 130) {
            x = GameConstants.GAME_SCREEN_WIDTH - 130;
        }
        if (y < 40) {
            y = 40;
        }
        if (y >= GameConstants.GAME_SCREEN_HEIGHT - 80) {
            y = GameConstants.GAME_SCREEN_HEIGHT - 80;
        }
    }
    @Override
    public void collisionHandler(Collidable c) {

    }

    @Override
    public void drawImage(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.img.getWidth() / 2.0, this.img.getHeight() / 2.0);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(this.img, rotation, null);

        //Hitbox outline for player
        g2.setColor(Color.RED);
        g2.drawRect(x, y, this.img.getWidth(), this.img.getHeight());
    }

}
