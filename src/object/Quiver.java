package object;

import entity.Entity;
import main.GamePanel;

public class Quiver extends Entity { // Tolba
    GamePanel gp;
    public Quiver(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickupOnly;
        value = 1;
        down1 = setup("/objects/quiver", gp.tileSize, gp.tileSize);
        name = "Quiver";
        image = setup("/objects/quiver", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/quiver_empty", gp.tileSize, gp.tileSize);
    }

    public boolean use(Entity entity) {
//        gp.playSE(2);
        gp.ui.addMessage("Ammo + " + value);
        entity.ammo += value;
        return true;
    }
}
