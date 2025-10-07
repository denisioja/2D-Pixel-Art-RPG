package monster; // --> enemy

import entity.Entity;
import entity.Projectile;
import main.GamePanel;
import object.Coin;
import object.Heart;
import object.Quiver;
import object.Stone;

import java.util.Random;

public class Slime extends Entity {

    GamePanel gp;

    public Slime(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_monster;
        name = "Breahna";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 4;
        life = maxLife;
        attack = 5;
        defense = 0;
        exp = 2;
        projectile = new Stone(gp);

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 40;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage() {
        up1 = setup("/monster/breahna_up1", gp.tileSize, gp.tileSize);
        up2 = setup("/monster/breahna_up2", gp.tileSize, gp.tileSize);
        down1 = setup("/monster/breahna_down1", gp.tileSize, gp.tileSize);
        down2 = setup("/monster/breahna_down2", gp.tileSize, gp.tileSize);
        left1 = setup("/monster/breahna_left1", gp.tileSize, gp.tileSize);
        left2 = setup("/monster/breahna_left2", gp.tileSize, gp.tileSize);
        right1 = setup("/monster/breahna_right1", gp.tileSize, gp.tileSize);
        right2 = setup("/monster/breahna_right2", gp.tileSize, gp.tileSize);

    }

    public void setAction() {
        if(onPath == true){

            // Check if it stops chasing
            checkStopChasingOrNot(gp.player, 15, 100);

            // search the direction to go
            searchPath(getGoalCol(gp.player),  getGoalRow(gp.player));

            // check if it shoots a projectile
            checkShootOrNot(200, 30);
        }
        else {
            // check if it starts chasing
            checkStopChasingOrNot(gp.player, 5, 100);

            // get a random direction
            getRandomDirection();
        }
    }

    @Override
    public void damageReaction() {
        actionLockCounter = 0;
//        direction = gp.player.direction; // it moves away from the player
        onPath = true;
    }

    public void checkDrop() {
        // Cast a die
        int i = new Random().nextInt(100) + 1;
        // Set the monster drop
        if(i < 50){
            dropItem(new Coin(gp));
        }
        if (i >= 50 && i < 75){
            dropItem(new Heart(gp));
        }
        if (i >= 75 && i < 100){
            dropItem(new Quiver(gp));
        }
    }
}
