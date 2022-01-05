package rainbowreef.game.gameObjects;

import rainbowreef.game.Resource;
import rainbowreef.game.gameObjects.moveable.Enemy;
import rainbowreef.game.gameObjects.stationary.Border;
import rainbowreef.game.gameObjects.stationary.BreakableBrick;
import rainbowreef.game.gameObjects.stationary.UnbreakableBrick;
import rainbowreef.game.gameObjects.stationary.powerUps.DoublePoints;
import rainbowreef.game.gameObjects.stationary.powerUps.ExtraLife;
import rainbowreef.game.gameObjects.stationary.powerUps.Secret;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Got this idea from the same Java brick breaker youtube video!
 * From Youtube: Java Game Programming - Develop a Brick Breaker Game
 *      Channel: Awais Mirza
 */

public class MapGenerator {

    ArrayList<GameObjects> gameObjects;
    ArrayList<Enemy> enemies;
    private int level = 1;
    private String value = "";
    String levelName;



    public MapGenerator(int level) {

        this.gameObjects = new ArrayList<>();
        this.enemies = new ArrayList<>();

        if(level == 1) {
            this.levelName = "maps/map1";

        } else if(level == 2) {
            this.levelName = "maps/map2";

        } else if(level == 3) {
            this.levelName = "maps/map3";

        } else if (level == 4) {
            this.levelName = "maps/tester";

        }

        try{

            InputStreamReader isr = new InputStreamReader(Display.class.getClassLoader().getResourceAsStream(levelName));
            BufferedReader mapReader = new BufferedReader(isr);

            String row = mapReader.readLine();

            if (row == null) {
                throw new IOException("No data in file!");
            }

            String[] mapInfo = row.split("\t");
            int numCols = Integer.parseInt(mapInfo[0]);
            int numRows = Integer.parseInt(mapInfo[1]);

            for(int curRow = 0; curRow < numRows; curRow++) {
                row = mapReader.readLine();
                mapInfo = row.split("\t");

                for(int curCol = 0; curCol < numCols; curCol++) {
                    switch(mapInfo[curCol]) {
                        /**
                         * Breakable brick variable legend:
                         * bbX_Y
                         * X = number of hits to break
                         * Y = version of brick image
                         * **/
                        case "1": //This will be the borders of the game
                            Border w = new Border(curCol * 20, curRow * 20, Resource.getResourceImage("border"));
                            this.gameObjects.add(w);
                            break;
                        case "2": //Block 1 this will 1 hit to break = 10points
                            BreakableBrick bb1_1 = new BreakableBrick(curCol * 20, curRow * 20, 2,  1,Resource.getResourceImage("blockEasy1"));
                            this.gameObjects.add(bb1_1);
                            break;
                        case "3": //Block 2 this will 1 hit to break = 20points
                            BreakableBrick bb1_2 = new BreakableBrick(curCol * 20, curRow * 20, 3, 2,Resource.getResourceImage("blockEasy2"));
                            this.gameObjects.add(bb1_2);
                            break;
                        case "4": //Block 3 this will 2 hits to break = 50points
                            BreakableBrick bb2_1 = new BreakableBrick(curCol * 20, curRow * 20, 4, 3,Resource.getResourceImage("blockMedium1"));
                            this.gameObjects.add(bb2_1);
                            break;
                        case "5": //Extra health
                            ExtraLife xl = new ExtraLife(curCol * 20, curRow * 20, 5, 1,Resource.getResourceImage("extraLife"));
                            this.gameObjects.add(xl);
                            break;
                        case "6": //Double points
                            DoublePoints dp = new DoublePoints(curCol * 20, curRow * 20, 6, 1,Resource.getResourceImage("doublePoints"));
                            this.gameObjects.add(dp);
                            break;
                        case "7": //Secret block will advance to next level = 10,0000 points 7 hits
                            Secret s = new Secret(curCol * 20, curRow * 20, 7, 7,Resource.getResourceImage("blockSolid"));
                            this.gameObjects.add(s);
                            break;

                        //Killing all enemies should advance to next level
                        case "8": //This will be the enemy, 1 hit to kill for now = 100 points
                            Enemy bl = new Enemy(curCol * 20, curRow * 20, 8, 1,Resource.getResourceImage("enemy1"));
                            this.enemies.add(bl);
                            break;

                        //Unbreakable bricks go here
                        case "9":
                            UnbreakableBrick ubr = new UnbreakableBrick(curCol * 20, curRow * 20, 9,Resource.getResourceImage("blockSolid"));
                            this.gameObjects.add(ubr);
                            break;

                    }
                }
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

    }

//    public void deleteBrick(Collidable c) {
//
//        if(c instanceof BreakableBrick || c instanceof DoublePoints || c instanceof ExtraLife || c instanceof Secret) {
//
//            this.gameObjects.remove(this.gameObjects.indexOf(c));
//        }
//        if(c instanceof Enemy) {
//
//            this.enemies.remove(this.enemies.indexOf(c));
//        }
//
//    }

    //Level helpers to advance levels
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}

