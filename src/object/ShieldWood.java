package object;

import entity.Entity;
import main.GamePanel;

public class ShieldWood extends Entity {

    public ShieldWood(GamePanel gp) {
        super(gp);

        type = type_shield;
        name = "Wood Shield";
        down1 = setup("/objects/shield", gp.tileSize, gp.tileSize);
        defenseValue = 1;
        description = "[" + name + "]\nMade from wood.";
        price = 50;
    }
}
