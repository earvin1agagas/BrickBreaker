package rainbowreef.game;

import rainbowreef.game.gameObjects.Display;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static javax.imageio.ImageIO.read;

public class Resource {

    private static Map<String, BufferedImage> resources;

    static {
        Resource.resources = new HashMap<>();
        try {
            Resource.resources.put("popImg", read(Display.class.getClassLoader().getResource("Pop.png")));
            Resource.resources.put("playerImg", read(Display.class.getClassLoader().getResource("Katch.png")));
            Resource.resources.put("blockEasy1", read(Display.class.getClassLoader().getResource("Block1.gif")));
            Resource.resources.put("blockEasy2", read(Display.class.getClassLoader().getResource("Block2.gif")));
            Resource.resources.put("blockMedium1", read(Display.class.getClassLoader().getResource("Block3.gif")));
            Resource.resources.put("blockMedium2", read(Display.class.getClassLoader().getResource("Block4.gif")));
            Resource.resources.put("blockSolid", read(Display.class.getClassLoader().getResource("Block_solid.gif")));
            Resource.resources.put("background", read(Display.class.getClassLoader().getResource("Background1.bmp")));
            Resource.resources.put("enemy1", read(Display.class.getClassLoader().getResource("Bigleg_small.png")));
            Resource.resources.put("extraLife", read(Display.class.getClassLoader().getResource("Block_life.gif")));
            Resource.resources.put("doublePoints", read(Display.class.getClassLoader().getResource("Block_split.gif")));
            Resource.resources.put("border", read(Display.class.getClassLoader().getResource("Wall.gif")));
            Resource.resources.put("heart", read(Display.class.getClassLoader().getResource("heart.png")));
            Resource.resources.put("gameOver", read(Display.class.getClassLoader().getResource("loserImg.jpg")));
            Resource.resources.put("youWon", read(Display.class.getClassLoader().getResource("you-won.gif")));
        } catch (IOException e) {
            e.printStackTrace();
            //Don't want to continue game if resources can't be read!
            System.exit(-5);
        }
    }

    public static BufferedImage getResourceImage(String key){return Resource.resources.get(key);}

}
