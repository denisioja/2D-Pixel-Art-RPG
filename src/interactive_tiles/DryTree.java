package interactive_tiles;

import entity.Entity;
import main.GamePanel;

import java.awt.*;

public class DryTree extends InteractiveTile{
    GamePanel gp;

    public DryTree(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        down1 = setup("/interactive_tiles/drytree", gp.tileSize, gp.tileSize);
        destructible = true;
        life = 3;
    }

    public boolean isCorrectItem(Entity entity) { // It checks if we use an axe to cut the tree
        boolean isCorrectItem = false;

        if (entity.currentWeapon.type == type_axe) {
            isCorrectItem = true;
        }

        return isCorrectItem;
    }

    public void playSE() {
//        gp.playSE(11);
    }

    public InteractiveTile getDistroyedForm() {
        InteractiveTile tile = new Trunk(gp, worldX/gp.tileSize, worldY/gp.tileSize);
        return tile;
    }

    public Color getPraticleColor() {
        Color color = new Color(98, 74, 33);
        return color;
    }

    public int getParticleSize() {
        int size = 6; // 6 pixels
        return size;
    }

    public int getParticleSpeed() {
        int speed = 1;
        return speed;
    }

    public int getParticleMaxLife() {
        int maxLife = 20; // It shows how much the particle lasts
        return maxLife;
    }
}
