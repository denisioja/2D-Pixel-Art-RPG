package object;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

import java.awt.*;

public class Spear extends Projectile { // Entity -> Projectile -> Spear

    GamePanel gp;

    public Spear(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Spear";
        speed = 5;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        knockBackPower = 0;
        useCost = 1;
        alive = false;
        getImage();
    }

    public void getImage() {
        up1 = setup("/projectile/spear_up", gp.tileSize, gp.tileSize);
        up2 = setup("/projectile/spear_up", gp.tileSize, gp.tileSize);
        down1 = setup("/projectile/spear_down", gp.tileSize, gp.tileSize);
        down2 = setup("/projectile/spear_down", gp.tileSize, gp.tileSize);
        left1 = setup("/projectile/spear_left", gp.tileSize, gp.tileSize);
        left2 = setup("/projectile/spear_left", gp.tileSize, gp.tileSize);
        right1 = setup("/projectile/spear_right", gp.tileSize, gp.tileSize);
        right2 = setup("/projectile/spear_right", gp.tileSize, gp.tileSize);
    }

    public boolean haveResource(Entity user) {
        boolean haveResource = false;
        if (user.ammo >= useCost) {
            haveResource = true;
        }
        return haveResource;
    }

    public void subtractResource(Entity user) {
        user.ammo -= useCost;
    }

    public Color getPraticleColor() {
        Color color = new Color(56, 56, 56);
        return color;
    }

    public int getParticleSize() {
        int size = 6; // 6 pixels
        return size;
    }

    public int getParticleSpeed() {
        int speed = 1;
        return speed;
    }

    public int getParticleMaxLife() {
        int maxLife = 20; // It shows how much the particle lasts
        return maxLife;
    }
}
