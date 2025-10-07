package main;

import ai.PathFinder;
import entity.Entity;
import entity.Player;
import environment.EnvironmentManager;
import interactive_tiles.InteractiveTile;
import tile.Map;
import tile.TileManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; //960 pixels
    public final int screenHeight = tileSize * maxScreenRow; //576 pixels

    final int nanosec = 1000000000;
    final int milisec = 1000000;

    //World Settings
    public final int maxWorldCol = 66;
    public final int maxWorldRow = 66;
    public final int maxMap = 10; // 10 different maps
    public int currentMap = 0;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    // For full screen
    int screenWidth2 = screenWidth; // the windows width
    int screenHeight2 = screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;
    public boolean fullScreenOn = false;

    // FPS
    int FPS = 60;

    // System
    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    Config config = new Config(this);
    public PathFinder pFinder = new PathFinder(this);
    EnvironmentManager eManager = new EnvironmentManager(this);
    Map map = new Map(this);
    Thread gameThread; //for the mechanics

    // Entity and object
    public Player player = new Player(this, keyH);
    public Entity obj[][] = new Entity[maxMap][20]; // we prepare 10 slots of objects, we display up to 10 object at the same time
    public Entity npc[][] = new Entity[maxMap][10];
    public Entity monster[][] = new Entity[maxMap][20]; // the total number we can display
    public InteractiveTile iTile[][] = new InteractiveTile[maxMap][50];
    public Entity projectile[][] = new Entity[maxMap][20];
//    public ArrayList<Entity> projectileList = new ArrayList<>();
    public ArrayList<Entity> particleList = new ArrayList<>();
    ArrayList<Entity> entityList = new ArrayList<>();

    // Game state
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;
    public final int optionsState = 5;
    public final int gameOverState = 6;
    public final int transitionState = 7;
    public final int tradeState = 8;
    public final int sleepState = 9;
    public final int mapState = 10;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // set the size ot this class (JPanel)
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // if set to true, all the drawing from this component will be done in an offscreen painting buffer. In short, enabling this can improve game's rendering performance.
        this.addKeyListener(keyH);
        this.setFocusable(true); // with this, the GamePanel can be "focused" to receive key input
    }

    public void setupGame() {
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        aSetter.setInteractiveTile();
        eManager.setup();
        playMusic(0);
        gameState = titleState;

        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB); // We created a blank bufferedImage as large as the screen size
        g2 = (Graphics2D) tempScreen.getGraphics();

        if(fullScreenOn == true) {
            setFullScreen();
        }
    }

    public void retry() {
        player.setDefaultPositions();
        player.restoreLifeAndAmmo();
        aSetter.setNPC();
        aSetter.setMonster();
    }

    public void restart() {
        player.setDefaultValues();
        player.setDefaultPositions();
        player.restoreLifeAndAmmo();
        player.setItems();
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        aSetter.setInteractiveTile();
    }

    public void setFullScreen() {
        // Get local screen device
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(Main.window);

        // Get full screen width and height
        screenWidth2 = Main.window.getWidth();
        screenHeight2 = Main.window.getHeight();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start(); // calls the run method
    }

    @Override
    public void run() {

        double drawInterval = nanosec / FPS; // 0.01666 seconds per frame
        double delta = 0; // the time passed since the last frame update.
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null) { // game loop

            currentTime = System.nanoTime();

            // Calculate the time elapsed since the last frame and add it to 'delta'.
            // This calculates how much time has passed relative to the desired 'drawInterval'.
            delta += (currentTime - lastTime) / drawInterval;

            // Add the elapsed time to the 'timer' for FPS calculation.
            timer += (currentTime - lastTime);

            // Update 'lastTime' with the current time for the next iteration.
            lastTime = currentTime;

            // Check if enough time has passed to draw a new frame ('delta' >= 1 means one frame's worth of time).
            if(delta >= 1) {
                // 1. UPDATE: update the information such as character position
                update();
                // 2. DRAW: draw the screen with the updated information
//                repaint(); // this is how you call the paintComponent function
                drawToTempScreen(); // draw everything to the buffered image
                drawToScreen(); // draw the buffered image to the screen
                // Decrement 'delta' because a frame has been drawn.
                delta--;

                // Increment the frame count.
                drawCount++;
            }

            if(timer >= nanosec) { // Check if one second has passed ('timer' >= 1 second in nanoseconds).
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        if (gameState == playState) {
            // player
            player.update();
            // npc
            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    npc[currentMap][i].update();
                }
            }
            // monster
            for (int i = 0; i < monster[1].length; i++) {
                if (monster[currentMap][i] != null) {
                    if(monster[currentMap][i].alive == true && monster[currentMap][i].dying == false) {
                        monster[currentMap][i].update();
                    }
                    if(monster[currentMap][i].alive == false) {
                        monster[currentMap][i].checkDrop();
                        monster[currentMap][i] = null;
                    }
                }
            }
            // projectile
            for (int i = 0; i < projectile[1].length; i++) {
                if (projectile[currentMap][i] != null) {
                    if(projectile[currentMap][i].alive == true ) {
                        projectile[currentMap][i].update();
                    }
                    if(projectile[currentMap][i].alive == false) {
                        projectile[currentMap][i] = null;
                    }
                }
            }

            // particle
            for (int i = 0; i < particleList.size(); i++) {
                if (particleList.get(i) != null) {
                    if(particleList.get(i).alive == true ) {
                        particleList.get(i).update();
                    }
                    if(particleList.get(i).alive == false) {
                        particleList.remove(i);
                    }
                }
            }

            // Interactive tiles
            for (int i = 0; i < iTile[1].length; i++) {
                if (iTile[currentMap][i] != null) {
                    iTile[currentMap][i].update();
                }
            }

            eManager.update();
        }
        if (gameState == pauseState) {
           // Nothing
        }
    }

    public void drawToTempScreen() {
        // Debug
        long drawStart = 0;
        if (keyH.showDebugText == true) {
            drawStart = System.nanoTime();
        }

        // Title screen
        if (gameState == titleState) {
            ui.draw(g2);
        }
        // Map screen
        else if(gameState == mapState) {
            map.drawFullMapScreen(g2);
        }
        else { // Others
            // TILE
            tileM.draw(g2);

            // Interactive tiles
            for (int i = 0; i < iTile[1].length; i++) {
                if(iTile[currentMap][i] != null) {
                    iTile[currentMap][i].draw(g2);
                }
            }

            // Add entities to the list
            entityList.add(player);

            for(int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    entityList.add(npc[currentMap][i]);
                }
            }

            for (int i = 0; i < obj[1].length; i++) {
                if (obj[currentMap][i] != null) {
                    entityList.add(obj[currentMap][i]);
                }
            }

            for (int i = 0; i < monster[1].length; i++) {
                if (monster[currentMap][i] != null) {
                    entityList.add(monster[currentMap][i]);
                }
            }

            // Projectile
            for (int i = 0; i < projectile[1].length; i++) {
                if (projectile[currentMap][i] != null) {
                    entityList.add(projectile[currentMap][i]);
                }
            }

            // particle
            for (int i = 0; i < particleList.size(); i++) {
                if (particleList.get(i) != null) {
                    entityList.add(particleList.get(i));
                }
            }

            // sort
            Collections.sort(entityList, new Comparator<Entity>() {

                @Override
                public int compare(Entity e1, Entity e2) {
                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return result;
                }
            });

            // Draw entities
            for (int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw(g2);
            }

            // Empty the list, otherwise the list keeps getting bigger
            entityList.clear();

            // environment
            eManager.draw(g2);

            // mini map
            map.drawMiniMap(g2);

            // UI
            ui.draw(g2);
        }

        // Debug
        if (keyH.showDebugText == true) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;

            g2.setFont(new Font("Arial", Font.PLAIN, 20));
            g2.setColor(Color.white);
            int x = 10;
            int y = 400;
            int lineHeight = 20;

            g2.drawString("WorldX: " + player.worldX, x, y);
            y += lineHeight;
            g2.drawString("WorldY: " + player.worldY, x, y);
            y += lineHeight;
            g2.drawString("Col: " + (player.worldX + player.solidArea.x)/tileSize, x, y);
            y += lineHeight;
            g2.drawString("Row: " + (player.worldY + player.solidArea.y)/tileSize, x, y);
            y += lineHeight;

            g2.drawString("Draw Time: " + passed, x, y);
//            System.out.println("Draw Time: " + passed);
        }
    }

    public void drawToScreen() {
        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
        g.dispose();
    }

    public void playMusic(int i)
    {
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic()
    {
        music.stop();
    }

    public void playSE(int i) {
        music.setFile(i);
        music.play();
    }
}


