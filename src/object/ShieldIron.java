package object;

import entity.Entity;
import main.GamePanel;

public class ShieldIron extends Entity {
    public ShieldIron(GamePanel gp) {
        super(gp);

        type = type_shield;
        name = "Iron Shield";
        down1 = setup("/objects/iron_shield", gp.tileSize, gp.tileSize);
        defenseValue = 2;
        description = "[" + name + "]\nMade from iron.";
        price = 75;
    }
}
