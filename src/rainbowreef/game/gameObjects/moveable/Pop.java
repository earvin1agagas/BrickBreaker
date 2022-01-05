package rainbowreef.game.gameObjects.moveable;

import rainbowreef.game.GameConstants;
import rainbowreef.game.gameObjects.Collidable;
import rainbowreef.game.gameObjects.Display;
import rainbowreef.game.gameObjects.GameObjects;
import rainbowreef.game.gameObjects.MapGenerator;
import rainbowreef.game.gameObjects.stationary.BreakableBrick;
import rainbowreef.game.gameObjects.stationary.Brick;
import rainbowreef.game.gameObjects.stationary.UnbreakableBrick;
import rainbowreef.game.gameObjects.stationary.powerUps.DoublePoints;
import rainbowreef.game.gameObjects.stationary.powerUps.ExtraLife;
import rainbowreef.game.gameObjects.stationary.powerUps.Secret;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Pop extends GameObjects implements Collidable {


    private int x, y, vx;
    private double vy;
    private float angle;
    //This should affect the y coordinate only
    private final double POP_SPEED = 1.7;
    private final int R = 2;
    private BufferedImage popImg;
    private boolean collision = false;
    private MapGenerator map;
    private int lives = 3;
    private Rectangle hitBox;


    private static int totalScore = 0;



    public Pop(int x, int y, int vx, double vy, int angle, BufferedImage popImg) {
        this.x = x;
        this.y = y;
        this.vx = 1;
        this.vy = POP_SPEED;
        this.angle = angle;
        this.popImg = popImg;
        this.hitBox = new Rectangle(x, y, this.popImg.getWidth(), this.popImg.getHeight());
    }

    @Override
    public Rectangle getHitBox() {
        return hitBox.getBounds();
    }


    @Override
    public void update() {
        movement();
        //To check y coord, stopping for some reason at whatever y is
        //System.out.println(y);
    }

    private void movement() {
        x += vx;
        y += vy;
        checkBorder();
        this.hitBox.setLocation(x, y);
        if(getLives() == 0) {
            vx = 0;
            vy = 0;
        }
    }

    /**
     * Using collision logic from youtube video for a brick breaker game in java and zetcode.com "breakout" game
     * Video name: Java Game Programming - Develop a Brick Breaker Game
     * Channel name: Awais Mirza
     */

    private void checkBorder() {

        //Left wall
        if(x < 21) {
            x = 21;
            vx = -vx;
        }
        //Right wall
        if(x > GameConstants.GAME_SCREEN_WIDTH - 70) {
            vx = -vx;
        }
        //Top wall
        if(y < 19) {
            y = 19;
            vy = -vy;
        }

        if(y > 670) {
            loseLives();
            System.out.println("You lost a life!");
        }

    }

    @Override
    public void collisionHandler(Collidable c) {


        if(this.getHitBox().intersects(c.getHitBox()) && Display.tickCount % 10 == 0) {
            if(c instanceof Player) {
                System.out.println("Pop hit paddle!");
                vy = -vy;
                System.out.println(((Player) c).getX());
                System.out.println(((Player) c).getY());
            }

            //Going to use individual if statements for troubleshooting each type of brick/powerup
            if(c instanceof UnbreakableBrick) {
                System.out.println("Pop hit an unbreakable brick!");
                //Left and right sides of bricks
                if(this.x + 14 <= ((UnbreakableBrick) c).getX() || this.x + 34 >= ((Brick) c).getX() + ((UnbreakableBrick) c).getWidth()) {
                    vx = -vx;
                } else {
                //Top and bottom of bricks?
                //if(y + 39 <= ((UnbreakableBrick) c).getY() || y + 39 >= ((UnbreakableBrick) c).getY() + ((UnbreakableBrick) c).getHeight()) {
                    vy = -vy;
                }

            }

            if(c instanceof BreakableBrick) {
                System.out.println("Pop hit breakable");

                //right and left
                if (this.x + 14 <= ((BreakableBrick) c).getX()|| this.x + 34 >= ((BreakableBrick) c).getX() + ((BreakableBrick) c).getWidth()) {
                    vx = -vx;
                }
                else {
                    vy = -vy;
                }

                    //Conditions for when bricks break!
                    switch (((BreakableBrick) c).getId()) {
                        case 2: //1 hit only
                            ((BreakableBrick) c).setHit(0);


                            totalScore += 10;
                            break;
                        case 3: //2 hit only

                            //Add score when block is completely destroyed
                            totalScore += 20;
                            break;
                        case 4: //3 hit only

                            //Add score when block is completely destroyed
                            totalScore += 50;
                            break;
                    }


                }

            if(c instanceof DoublePoints) {
                System.out.println("Pop hit a powerup");
                System.out.println(((DoublePoints) c).getX());
                System.out.println(((DoublePoints) c).getWidth());
                if(x + 14 <= ((DoublePoints) c).getX() || x + 34 >= ((DoublePoints) c).getX() + ((DoublePoints) c).getWidth()) {
                    vx = -vx;
                }
                else {
                    vy = -vy;
                }
                //Add double points per block


            }

            if(c instanceof ExtraLife) {
                System.out.println("Pop hit a powerup");
                System.out.println(((ExtraLife) c).getX());
                System.out.println(((ExtraLife) c).getWidth());

                if(this.x + 14 <= ((ExtraLife) c).getX() || this.x + 34 >= ((ExtraLife) c).getX() + ((ExtraLife) c).getWidth()) {
                    vx = -vx;
                }
                else {
                    vy = -vy;
                }

                if(lives == 3) {

                } else {
                    lives++;
                }

            }

            if(c instanceof Secret) {
                System.out.println("Pop hit a powerup");

                if(this.x + 14 <= ((Secret) c).getX() || this.x + 34 >= ((Secret) c).getX() + ((Secret) c).getWidth()) {
                    vx = -vx;
                }
                else {
                    vy = -vy;
                }
                //Add Secret score when block is completely destroyed

                if (((Secret) c).getHit() > 1) {
                    System.out.println("This block seems different");
                    int hits = ((Secret) c).getHit() - 1;
                    ((Secret) c).setHit(hits);
                } else {
                    totalScore += 10000;
                    System.out.println("You found a secret block!!!");
                }

            }


            if(c instanceof Enemy) {
                System.out.println("Pop hit an enemy!");
                System.out.println(((Enemy) c).getWidth());
                System.out.println(((Enemy) c).getX());
                //Left and right sides of enemies
                if(this.x + 14 <= ((Enemy) c).getX() || this.x + 34 >= ((Enemy) c).getX() + ((Enemy) c).getWidth()) {
                    vx = -vx;
                } else {
                    vy = -vy;
                }

                totalScore += 100;

            }

            //totalScore += totalScore;

        }



    }

    public int getTotalScore() {
        return this.totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

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
    public int getVx() {
        return vx;
    }

    @Override
    public void setVx(int vx) {
        this.vx = vx;
    }

    @Override
    public double getVy() {
        return vy;
    }

    @Override
    public void setVy(int vy) {
        this.vy = vy;
    }

    public int getLives() {
        return this.lives;
    }

    public void loseLives() {
        if (lives > 0) {
            lives -= 1;
            vy = POP_SPEED;
            setX(GameConstants.GAME_SCREEN_WIDTH / 2);
            setY(300);
        } else {
            vx = 0;
            vy = 0;
            lives = 0;
        }
    }




    @Override
    public void drawImage(Graphics g) {
//        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        //rotation.rotate(Math.toRadians(angle), this.popImg.getWidth(), this.popImg.getHeight());
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(this.popImg, x, y, this.popImg.getWidth(),this.popImg.getHeight(), null);

        //Hitbox outline for starfish
        g2.setColor(Color.BLUE);
        g2.drawRect(x, y, this.popImg.getWidth(), this.popImg.getHeight());
    }




}
