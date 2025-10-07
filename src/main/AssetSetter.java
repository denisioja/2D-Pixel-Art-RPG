package main;

import entity.Merchant;
import entity.OldMan;
import interactive_tiles.DryTree;
import monster.Slime;
import monster.Soldier;
import object.*;
import tile.Tile;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        int mapNum = 0;
        int i = 0;
        gp.obj[mapNum][i] = new Coin(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 53;
        gp.obj[mapNum][i].worldY = gp.tileSize * 33;
        i++;
        gp.obj[mapNum][i] = new Coin(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 52;
        gp.obj[mapNum][i].worldY = gp.tileSize * 34;
        i++;
        gp.obj[mapNum][i] = new Coin(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 51;
        gp.obj[mapNum][i].worldY = gp.tileSize * 35;
        i++;
        gp.obj[mapNum][i] = new Axe(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 46;
        gp.obj[mapNum][i].worldY = gp.tileSize * 10;
        i++;
//        gp.obj[mapNum][i] = new ShieldIron(gp);
//        gp.obj[mapNum][i].worldX = gp.tileSize * 49;
//        gp.obj[mapNum][i].worldY = gp.tileSize * 35;
//        i++;
        gp.obj[mapNum][i] = new PotionRed(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 33;
        gp.obj[mapNum][i].worldY = gp.tileSize * 31;
        i++;
//        gp.obj[mapNum][i] = new PotionRed(gp);
//        gp.obj[mapNum][i].worldX = gp.tileSize * 22;
//        gp.obj[mapNum][i].worldY = gp.tileSize * 35;
//        i++;
//        gp.obj[mapNum][i] = new PotionRed(gp);
//        gp.obj[mapNum][i].worldX = gp.tileSize * 22;
//        gp.obj[mapNum][i].worldY = gp.tileSize * 40;
//        i++;
        gp.obj[mapNum][i] = new Heart(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 42;
        gp.obj[mapNum][i].worldY = gp.tileSize * 37;
        i++;
        gp.obj[mapNum][i] = new Quiver(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 10;
        gp.obj[mapNum][i].worldY = gp.tileSize * 7;
        i++;
//        gp.obj[mapNum][i] = new Door(gp);
//        gp.obj[mapNum][i].worldX = gp.tileSize * 17;
//        gp.obj[mapNum][i].worldY = gp.tileSize * 27;
//        i++;
        gp.obj[mapNum][i] = new Door(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 11;
        gp.obj[mapNum][i].worldY = gp.tileSize * 32;
        i++;
        gp.obj[mapNum][i] = new Chest(gp, new ShieldIron(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize * 10;
        gp.obj[mapNum][i].worldY = gp.tileSize * 32;
        i++;
        gp.obj[mapNum][i] = new Torch(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 20;
        gp.obj[mapNum][i].worldY = gp.tileSize * 33;
        i++;
        gp.obj[mapNum][i] = new CampFire(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 31;
        gp.obj[mapNum][i].worldY = gp.tileSize * 47;
        i++;
        gp.obj[mapNum][i] = new Door(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 49;
        gp.obj[mapNum][i].worldY = gp.tileSize * 40;
        i++;
    }

    public void setNPC() {
        int mapNum = 0;
        int i = 0;
        // Map 0
        gp.npc[mapNum][i] = new OldMan(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 29;
        gp.npc[mapNum][i].worldY = gp.tileSize * 25;
        i++;

        // Map 1
        mapNum = 1;
        i = 0;
        gp.npc[mapNum][i] = new Merchant(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 21;
        gp.npc[mapNum][i].worldY = gp.tileSize * 26;
    }

    public void setMonster() {
        int mapNum = 0;
        int i = 0;
        gp.monster[mapNum][i] = new Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 48;
        gp.monster[mapNum][i].worldY = gp.tileSize * 7;
        i++;
        gp.monster[mapNum][i] = new Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 42;
        gp.monster[mapNum][i].worldY = gp.tileSize * 54;
        i++;
        gp.monster[mapNum][i] = new Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 10;
        gp.monster[mapNum][i].worldY = gp.tileSize * 9;
        i++;
        gp.monster[mapNum][i] = new Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 39;
        gp.monster[mapNum][i].worldY = gp.tileSize * 26;
        i++;
        gp.monster[mapNum][i] = new Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 42;
        gp.monster[mapNum][i].worldY = gp.tileSize * 25;

        i++;
        gp.monster[mapNum][i] = new Soldier(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 10;
        gp.monster[mapNum][i].worldY = gp.tileSize * 35;

        i++;
        gp.monster[mapNum][i] = new Soldier(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 51;
        gp.monster[mapNum][i].worldY = gp.tileSize * 42;
    }

    public void setInteractiveTile() {
        int mapNum = 0;
        int i = 0;
        gp.iTile[mapNum][i] = new DryTree(gp, 22, 18);
        i++;
        gp.iTile[mapNum][i] = new DryTree(gp, 23, 18);
        i++;
        gp.iTile[mapNum][i] = new DryTree(gp, 24, 18);
        i++;
        gp.iTile[mapNum][i] = new DryTree(gp, 25, 18);
        i++;
        gp.iTile[mapNum][i] = new DryTree(gp, 26, 18);
        i++;
        gp.iTile[mapNum][i] = new DryTree(gp, 27, 18);
        i++;
        gp.iTile[mapNum][i] = new DryTree(gp, 28, 18);
        i++;
        gp.iTile[mapNum][i] = new DryTree(gp, 29, 18);
        i++;
        gp.iTile[mapNum][i] = new DryTree(gp, 30, 18);
        i++;

    }
}
