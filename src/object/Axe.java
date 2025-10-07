package object;

import entity.Entity;
import main.GamePanel;

public class Axe extends Entity {
    public Axe(GamePanel gp) {
        super(gp);

        type = type_axe;
        name = "Axe";
        description = "[" + name + "]\nZamolxe's axe.";
        down1 = setup("/objects/axe", gp.tileSize, gp.tileSize);
        attackValue = 2;
        attackArea.width = 30;
        attackArea.height = 30;
        price = 75;
        knockBackPower = 10;

        motion1_duration = 20;
        motion2_duration = 40;
    }
}
