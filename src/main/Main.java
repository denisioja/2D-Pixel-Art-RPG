package main;

import javax.swing.JFrame;

public class Main {

    public static JFrame window;

    public static void main(String[] args) {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //this lets the window properly close when user clicks the close ("x") button.
        window.setResizable(false);
        window.setTitle("2D Pixel Art RPG");
//        window.setUndecorated(true); sets if you want to draw the upper bar or not

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        gamePanel.config.loadConfig();
        if(gamePanel.fullScreenOn == true) {
            window.setUndecorated(true);
        }

        window.pack(); //we pack it so we see it

        window.setLocationRelativeTo(null); //Not specify the location of the window => The window will be displayed at the center of the screen.
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
        
    }
}
