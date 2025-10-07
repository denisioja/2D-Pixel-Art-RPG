package object;

import main.GamePanel;
import entity.Entity;

public class SwordNormal extends Entity {

    public SwordNormal(GamePanel gp) {
        super(gp);

        type = type_sword;
        name = "Normal Sword";
        down1 = setup("/objects/sword", gp.tileSize, gp.tileSize);
        attackValue = 1;
        attackArea.width = 36;
        attackArea.height = 36;
        description = "[" + name + "]\nAn old sword.";
        price = 25;
        knockBackPower = 2;
        motion1_duration = 5;
        motion2_duration = 25;
    }
}
