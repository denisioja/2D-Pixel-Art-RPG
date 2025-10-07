package object;

import entity.Entity;
import main.GamePanel;

public class Key extends Entity {
    GamePanel gp;

    public Key(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = "Key";
        down1 = setup("/objects/key", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nIt opens a door.";
        price = 50;
        stackable = true;
    }

    public boolean use(Entity entity) {
        gp.gameState = gp.dialogueState;
        int objIndex = getDetected(entity, gp.obj, "Door");

        if(objIndex != 999) {
            gp.ui.currentDialogue = "You used the " + name + " and opened he door";
//            gp.playSE(3);
            gp.obj[gp.currentMap][objIndex] = null;
            return true;
        }
        else {
            gp.ui.currentDialogue = "What are you doing?";
            return false;
        }
    }
}
