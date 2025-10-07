package entity;

import main.GamePanel;

public class Projectile extends Entity{

    Entity user;

    public Projectile(GamePanel gp) {
        super(gp);
    }

    public void set(int worldX, int worldY, String direction, boolean alive, Entity user) { // the user is if it were for different characters to have this ability
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.alive = alive;
        this.user = user;
        this.life = this.maxLife; // Reset life to max every time you shoot
    }

    public void update() {

        if (user == gp.player) {
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            if (monsterIndex != 999) {
                gp.player.damageMonster(monsterIndex, this, attack, knockBackPower); // the attack is the damage of the projectile
                generateParticle(user.projectile, gp.monster[gp.currentMap][monsterIndex]);
                alive = false; // if the projectile hits a monster is dies
            }
        }
        if (user != gp.player) {
            boolean contactPlayer = gp.cChecker.checkPlayer(this);
            if (gp.player.invincible == false && contactPlayer == true) {
                damagePlayer(attack);
                generateParticle(user.projectile, gp.player);
                alive = false;
            }
        }

        switch (direction) {
            case "up":
                worldY -= speed;
                break;
            case "down":
                worldY += speed;
                break;
            case "left":
                worldX -= speed;
                break;
            case "right":
                worldX += speed;
                break;
        }

        life--;
        if (life <= 0) {
            alive = false; // once you shoot a projectile, is slowly looses life until it disappeares
        }

        spriteCounter++;
        if (spriteCounter > 12) {
            if (spriteNum == 1)
                spriteNum = 2;
            else if (spriteNum == 2)
                spriteNum = 1;
            spriteCounter = 0;

        }
    }

    public boolean haveResource(Entity user) {
        boolean haveResource = false;
        return haveResource;
    }

    public void subtractResource(Entity user) {}
}
