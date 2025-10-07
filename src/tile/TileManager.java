package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][][];
    boolean drawPath = true;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[50]; //50 kinds of tiles
        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/world01.txt", 0);
        loadMap("/maps/interior1.txt", 1);
    }

    public void getTileImage() {
        // We use 0-9 as placeholders so we prevent NullPointer exception, also single digit will ruin the map matrix
        setup(0, "grass", false);
        setup(1, "grass", false);
        setup(2, "grass", false);
        setup(3, "grass", false);
        setup(4, "grass", false);
        setup(5, "grass", false);
        setup(6, "grass", false);
        setup(7, "grass", false);
        setup(8, "grass", false);
        setup(9, "grass", false);

        // Actual tiles
        setup(10, "grass", false);

        setup(11, "water", true);
        setup(12, "water1", true);
        setup(13, "water2", true);
        setup(14, "water3", true);
        setup(15, "water4", true);
        setup(16, "water5", true);
        setup(17, "water6", true);
        setup(18, "water7", true);
        setup(19, "water8", true);
        setup(20, "water9", true);
        setup(21, "water10", true);
        setup(22, "water11", true);
        setup(23, "water12", true);

        setup(24, "road", false);
        setup(25, "road1", false);
        setup(26, "road2", false);
        setup(27, "road3", false);
        setup(28, "road4", false);
        setup(29, "road5", false);
        setup(30, "road6", false);
        setup(31, "road7", false);
        setup(32, "road8", false);
        setup(33, "road9", false);
        setup(34, "road10", false);
        setup(35, "road11", false);
        setup(36, "road12", false);

        setup(37, "dirt", false);
        setup(38, "wall", true);
        setup(39, "tree", true);
        setup(40, "hut", false);
        setup(41, "floor1", false);
        setup(42, "table1", true);
    }

    public void setup(int index, String imageName, boolean collision) {
        UtilityTool uTool = new UtilityTool();
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath, int map) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            while(col < gp.maxWorldCol && row < gp.maxWorldRow) { //read the text file
                String line = br.readLine(); // read a line of text

                while(col < gp.maxWorldCol) {
                    String numbers[] = line.split(" "); // splits the string around match of the given regular expression

                    int num = Integer.parseInt(numbers[col]); // e change the String to int
                    mapTileNum[map][col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        }
        catch (Exception e) {
//            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;//initialize the column index for the world map
        int worldRow = 0;// --||--

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            //Loop through each tile in the world map
            int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];//Get the tile number form the map data
            //The Camera
            int worldX = worldCol * gp.tileSize;// calculate the world X-coordinate of the current tile
            int worldY = worldRow * gp.tileSize; // --||--
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY= worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
               worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
               worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) { //the game randers only the tiles that are at a certain distance from the player
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }

            worldCol++;

            if(worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
        if(drawPath == true) {
            g2.setColor(new Color(255, 0, 0, 70));

            for(int i = 0; i < gp.pFinder.pathList.size(); i++) {
                int worldX = gp.pFinder.pathList.get(i).col * gp.tileSize;
                int worldY = gp.pFinder.pathList.get(i).row * gp.tileSize;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY= worldY - gp.player.worldY + gp.player.screenY;

//                g2.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
            }
        }
    }
}
