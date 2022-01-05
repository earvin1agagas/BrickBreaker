package rainbowreef.game;

import rainbowreef.game.gameObjects.Display;
import rainbowreef.menus.EndGamePanel;
import rainbowreef.menus.StartMenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class Launcher {

    private JPanel mainPanel;

    private JPanel startPanel;

    private Display gamePanel;

    private JPanel endPanel;

    private JFrame jf;

    private CardLayout cl;

    public Launcher() {
        this.jf = new JFrame();             // creating a new JFrame object
        this.jf.setTitle("Rainbow Reef"); // setting the title of the JFrame window.
        this.jf.setLocationRelativeTo(null); // this will open the window in the center of the screen.
        this.jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // when the GUI is closed, this will also shutdown the VM
    }

    private void initUIComponents(){
        this.mainPanel = new JPanel(); // create a new main panel
        this.startPanel = new StartMenuPanel(this); // create a new start panel
        this.gamePanel = new Display(this); // create a new game panel
        this.gamePanel.gameInitialize(); // initialize game, but DO NOT start game
        this.endPanel = new EndGamePanel(this); // create a new end game pane;
        cl = new CardLayout(); // creating a new CardLayout Panel
        this.jf.setResizable(false); //make the JFrame not resizable
        this.mainPanel.setLayout(cl); // set the layout of the main panel to our card layout
        this.mainPanel.add(startPanel, "start"); //add the start panel to the main panel
        this.mainPanel.add(gamePanel, "game");   //add the game panel to the main panel
        this.mainPanel.add(endPanel, "end");    // add the end game panel to the main panel
        this.jf.add(mainPanel); // add the main panel to the JFrame
        this.setFrame("start"); // set the current panel to start panel
    }

    public void setFrame(String type){
        this.jf.setVisible(false); // hide the JFrame
        switch(type){
            case "start":
                // set the size of the jFrame to the expected size for the start panel
                this.jf.setSize(GameConstants.START_MENU_SCREEN_WIDTH,GameConstants.START_MENU_SCREEN_HEIGHT);
                break;
            case "game":
                // set the size of the jFrame to the expected size for the game panel
                this.jf.setSize(GameConstants.GAME_SCREEN_WIDTH,GameConstants.GAME_SCREEN_HEIGHT);
                //start a new thread for the game to run. This will ensure our JFrame is responsive and
                // not stuck executing the game loop.
                (new Thread(this.gamePanel)).start();
                break;
            case "end":
                // set the size of the jFrame to the expected size for the end panel
                this.jf.setSize(GameConstants.END_MENU_SCREEN_WIDTH,GameConstants.END_MENU_SCREEN_HEIGHT);
                break;
        }
        this.cl.show(mainPanel, type); // change current panel shown on main panel tp the panel denoted by type.
        this.jf.setVisible(true); // show the JFrame
    }

    public JFrame getJf() {
        return jf;
    }

    public void closeGame(){
        this.jf.dispatchEvent(new WindowEvent(this.jf, WindowEvent.WINDOW_CLOSING));
    }


    public static void main(String[] args) {
        Launcher launch = new Launcher();
        launch.initUIComponents();
    }

}
