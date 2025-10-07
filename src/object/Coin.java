package object;

import entity.Entity;
import main.GamePanel;

public class Coin extends Entity {
    GamePanel gp;
    public Coin(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickupOnly;
        name = "Bronze Coin";
        value = 1;
        down1 = setup("/objects/coin", gp.tileSize, gp.tileSize);
    }

    public boolean use(Entity entity) {
//        gp.playSE(1);
        gp.ui.addMessage("Coin + " + value);
        gp.player.coin += value;
        return true;
    }


}
