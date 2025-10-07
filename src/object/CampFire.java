package object;

import entity.Entity;
import main.GamePanel;

public class CampFire extends Entity {
    GamePanel gp;
    public CampFire(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = "Camp Fire";
        down1 = setup("/objects/campfire", gp.tileSize, gp.tileSize);
        down2 = setup("/objects/campfire2", gp.tileSize, gp.tileSize);

        description = "[Camp Fire]\nYou can rest here\nuntil the morning.";
        price = 300;
        stackable = true;
    }

    public boolean use(Entity entity) {
        gp.gameState = gp.sleepState;
//        gp.playSE(14);
        gp.player.life = gp.player.maxLife;
        gp.player.ammo = gp.player.maxAmmo;
        gp.player.getSleepingImage(down2);
        return true;
    }
}
