package entity;

import main.GamePanel;

import java.awt.*;
import java.util.Random;

public class OldMan extends Entity {
    public OldMan(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;

        solidAreaDefaultX = 8;
        solidAreaDefaultY = 16;

        getImage();
        setDialogue();
    }

    public void getImage() {
        up1 = setup("/npc/oldman_up1", gp.tileSize, gp.tileSize);
        up2 = setup("/npc/oldman_up2", gp.tileSize, gp.tileSize);
        down1 = setup("/npc/oldman_down1", gp.tileSize, gp.tileSize);
        down2 = setup("/npc/oldman_down2", gp.tileSize, gp.tileSize);
        left1 = setup("/npc/oldman_left1", gp.tileSize, gp.tileSize);
        left2 = setup("/npc/oldman_left2", gp.tileSize, gp.tileSize);
        right1 = setup("/npc/oldman_right1", gp.tileSize, gp.tileSize);
        right2 = setup("/npc/oldman_right2", gp.tileSize, gp.tileSize);
    }

    public void setDialogue() {
        dialogues[0] = "Hello, lad.";
        dialogues[1] = "So you've come to this island to \nfind the treasure?";
        dialogues[2] = "I used to be a great wizard, but now.. \nI'm too old for adventures. ";
        dialogues[3] = "Well good luck on you!";
    }

    public void setAction() { // some sort of AI
        if(onPath == true) {
//            int goalCol = 10;
//            int goalRow = 8;
            int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;

            searchPath(goalCol, goalRow);
        }
        else {
            actionLockCounter ++;

            if (actionLockCounter == 120) { // it picks a direction every 2 seconds
                Random random = new Random();
                int i = random.nextInt(100)+1; // random number from 1->100

                if (i <= 25) {
                    direction = "up";
                }
                if (i > 25 && i <= 50) {
                    direction = "down";
                }
                if (i > 50 && i <= 75) {
                    direction = "left";
                }
                if (i > 75 && i <= 100) {
                    direction = "right";
                }
                actionLockCounter = 0;
            }
        }

    }

    public void speak() {

        super.speak();
        onPath = true;
    }
}
