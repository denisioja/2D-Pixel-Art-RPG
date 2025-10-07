package object;

import entity.Entity;
import main.GamePanel;

public class Door extends Entity {

    GamePanel gp;

    public Door(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = "Door";
        down1 = setup("/objects/door", gp.tileSize, gp.tileSize);
        collision = true;

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }

    public void interact() {
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You need a key to open it.";
    }
}
