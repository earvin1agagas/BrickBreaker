package rainbowreef.game.gameObjects.moveable;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerControl implements KeyListener {

    private Player p;
    private final int left;
    private final int right;
    private final int space;

    public PlayerControl(Player p, int left, int right, int space) {
        this.p = p;
        this.left = left;
        this.right = right;
        this.space = space;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyPressed = e.getKeyCode();

        if(keyPressed == left) {
            this.p.toggleLeftPressed();
        }

        if(keyPressed == right) {
            this.p.toggleRightPressed();
        }

        if(keyPressed == space) {
            this.p.toggleSpacePressed();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyReleased = e.getKeyCode();

        if(keyReleased == left) {
            this.p.unToggleLeftPressed();
        }

        if(keyReleased == right) {
            this.p.unToggleRightPressed();
        }

        if(keyReleased == space) {
            this.p.unToggleSpacePressed();
        }


    }
}
