package object;

import entity.Entity;
import main.GamePanel;

public class Torch extends Entity {
    public Torch(GamePanel gp) {
        super(gp);
        type = type_light;
        name = "Torch";
        down1 = setup("/objects/torch", gp.tileSize, gp.tileSize);
        description = "[Torch]\nIluminates your\nsurroundings.";
        price = 150;
        lightRadius = 250;
    }
}
