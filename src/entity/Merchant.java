package entity;

import main.GamePanel;
import object.*;

public class Merchant extends Entity{
    public Merchant(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;

        getImage();
        setDialogue();
        setItems();
    }

    public void getImage() {
        up1 = setup("/npc/merchant_down1", gp.tileSize, gp.tileSize);
        up2 = setup("/npc/merchant_down1", gp.tileSize, gp.tileSize);
        down1 = setup("/npc/merchant_down1", gp.tileSize, gp.tileSize);
        down2 = setup("/npc/merchant_down1", gp.tileSize, gp.tileSize);
        left1 = setup("/npc/merchant_down1", gp.tileSize, gp.tileSize);
        left2 = setup("/npc/merchant_down1", gp.tileSize, gp.tileSize);
        right1 = setup("/npc/merchant_down1", gp.tileSize, gp.tileSize);
        right2 = setup("/npc/merchant_down1", gp.tileSize, gp.tileSize);
    }

    public void setDialogue() {
        dialogues[0] = "He he, so you found me. \nI have some good stuff.\nDo you want to trade?";
    }

    public void setItems() {
        inventory.add(new PotionRed(gp));
        inventory.add(new Key(gp));
        inventory.add(new SwordNormal(gp));
        inventory.add(new Axe(gp));
        inventory.add(new ShieldWood(gp));
        inventory.add(new ShieldIron(gp));

    }

    public void speak() {
        super.speak();
        gp.gameState = gp.tradeState;
        gp.ui.npc = this;
    }
}

