package rainbowreef.game.gameObjects;

import rainbowreef.game.GameConstants;
import rainbowreef.game.Launcher;
import rainbowreef.game.Resource;
import rainbowreef.game.gameObjects.moveable.Enemy;
import rainbowreef.game.gameObjects.moveable.Player;
import rainbowreef.game.gameObjects.moveable.PlayerControl;
import rainbowreef.game.gameObjects.moveable.Pop;
import rainbowreef.game.gameObjects.stationary.Brick;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;


public class Display extends JPanel implements Runnable {

    private BufferedImage world;
    private Launcher lf;
    private long tick = 0;
    public static long tickCount = 0;
    private static boolean gameOver = false;
    public static boolean pause = false;
    private MapGenerator map;
    public int level = 1;


    public Display(Launcher lf) {this.lf = lf;}

    @Override
    public void run() {
        try {
            this.resetGame();
            while(!gameOver) {
                if(level == 1 || level == 2 || level == 3) {
                this.tick++;
                    this.map.gameObjects.forEach(gameObject -> gameObject.update());
                    this.map.enemies.forEach(enemy -> enemy.update());
                    this.repaint();
                    Thread.sleep(1000 / 144);
                    tickCount++;

                    Player p = (Player) this.map.gameObjects.get(this.map.gameObjects.size() - 1); //Because player is getting added last to array list
                    Pop b = (Pop) this.map.gameObjects.get(this.map.gameObjects.size() - 2);

                    //Add collision handler here for player and pop
                    b.collisionHandler(p);

                    //Collision handler for all bricks and powerups, etc.
                    for (int i = 0; i < this.map.gameObjects.size() - 3; i++) {
                        Brick br = (Brick) this.map.gameObjects.get(i);
                        b.collisionHandler(br);
                    }

                    //Collision handler for enemies making separate for advancing level conditions ---idk if this will help or not yet
                    for (int i = 0; i < this.map.enemies.size() - 1; i++) {
                        Enemy e = this.map.enemies.get(i);
                        b.collisionHandler(e);
                    }


                }

            }
        } catch (InterruptedException ignored) {
            System.out.println(ignored);
        }
    }

    public void resetGame() {

        this.tick = 0;

        Player p = (Player) this.map.gameObjects.get(this.map.gameObjects.size() - 1);
        Pop b = (Pop) this.map.gameObjects.get(this.map.gameObjects.size() - 2);

        //Should set paddle in the bottom middle of the screen
        p.setX(GameConstants.GAME_SCREEN_WIDTH / 2);
        p.setY(640);

        b.setX(GameConstants.GAME_SCREEN_WIDTH / 2);
        b.setY(300);



    }

    public void gameInitialize() {

        this.world = new BufferedImage( GameConstants.WORLD_WIDTH,
                                        GameConstants.WORLD_HEIGHT,
                                        BufferedImage.TYPE_INT_RGB);



        map = new MapGenerator(1);
        map = new MapGenerator(2);
        map = new MapGenerator(3);
        map = new MapGenerator(level);




        Pop b = new Pop(GameConstants.GAME_SCREEN_WIDTH / 2, 300, 0, 0, 0, Resource.getResourceImage("popImg"));
        Player p = new Player(GameConstants.GAME_SCREEN_WIDTH / 2,640, 0, Resource.getResourceImage("playerImg"));

        //Added spacebar for pause, need to get it working properly
        PlayerControl pc = new PlayerControl(p, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_SPACE);

        this.map.gameObjects.add(b); //2nd to last object added (b for ball)
        this.map.gameObjects.add(p); //Last object added to arrayList

        for(int i = 0; i < this.map.enemies.size() - 1; i++) {

        }
        //add the starfish here
        this.setBackground(Color.BLACK);
        this.lf.getJf().addKeyListener(pc);




    }


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        Graphics2D buffer = world.createGraphics();
        //Background
        buffer.drawImage(Resource.getResourceImage("background"), 0, 0, GameConstants.GAME_SCREEN_WIDTH, GameConstants.GAME_SCREEN_HEIGHT, null);

        //buffer.fillRect(0,0,GameConstants.WORLD_WIDTH,GameConstants.WORLD_HEIGHT);

        //These dimensions are about where I want the paddle to be at.
        //g2.drawImage(Resource.getResourceImage("playerImg"), GameConstants.GAME_SCREEN_WIDTH / 2, 800, null);

        //Drawing: paddle, pop, bricks, powerups
        //this.gameObjects.forEach(gameObject -> gameObject.drawImage(buffer));
        //Drawing: enemies
        //this.enemies.forEach(enemy -> enemy.drawImage(buffer));

        this.map.gameObjects.forEach(gameObject -> gameObject.drawImage(buffer));
        this.map.enemies.forEach(enemy -> enemy.drawImage(buffer));

        Pop p = (Pop) this.map.gameObjects.get(this.map.gameObjects.size() - 2);

        g2.drawImage(world, 0, 0, null);
        //Player p = (Player) this.gameObjects.get(this.gameObjects.size() - 1);
        //starfish this.gameObjects.size() - 2;

        //Drawing lives here from tank game!
        int heartPlace;
        for(int i = 1; i <= p.getLives(); i++) {
            heartPlace = ((Resource.getResourceImage("heart").getWidth() + 20) * i) / 2;
            g2.drawImage(Resource.getResourceImage("heart"), heartPlace, 660, null);
        }

        //Drawing score here!
        if(!gameOver) {
            int score = p.getTotalScore();

            g2.setColor(Color.BLUE);
            g2.setFont(new Font("arial", Font.BOLD, 30));
            g2.drawString("Score: " + score, 50 ,750);
            if(score == 0) {
                level = 1;
            }

            if(score > 100) {
                level = 2;
            }
            if(score > 200) {
                level = 3;
            }

            //GameOver Win Screen
            if(score > 500) {

                p.setVx(0);
                p.setVy(0);
                p.setX(GameConstants.GAME_SCREEN_WIDTH / 2);
                p.setY(300);
                p.setTotalScore(500);
                gameOver = true;
                g2.drawImage(Resource.getResourceImage("youWon"), GameConstants.GAME_SCREEN_WIDTH / 4, GameConstants.GAME_SCREEN_HEIGHT / 8, 300, 400, null);
                g2.drawString("YOU BEAT THE GAME!", GameConstants.GAME_SCREEN_WIDTH / 4,GameConstants.GAME_SCREEN_HEIGHT / 8);
            }
        }

        //GameOver Lose Screen
        if(p.getLives() == 0) {

            int score = p.getTotalScore();
            g2.drawImage(Resource.getResourceImage("gameOver"), GameConstants.GAME_SCREEN_WIDTH / 4, GameConstants.GAME_SCREEN_HEIGHT / 8, 300, 400 , null);
            g2.drawString("Total Score: " + score, GameConstants.GAME_SCREEN_WIDTH / 4,GameConstants.GAME_SCREEN_HEIGHT / 8);
        }


        //Need to figure out on key press
        if(pause) {
            g2.setColor(Color.RED);
            g2.fillRect(GameConstants.GAME_SCREEN_WIDTH / 4, GameConstants.GAME_SCREEN_HEIGHT / 8, 300, 400);
        }
    }

    //Need to figure out on key press
    public static boolean setPause(boolean pause) {
        return pause;
    }


}
