package monster;

import entity.Entity;
import main.GamePanel;
import object.Coin;
import object.Heart;
import object.Quiver;
import object.Stone;

import java.util.Random;

public class Soldier extends Entity {
    GamePanel gp;

    public Soldier(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_monster;
        name = "Roman Soldier";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 8;
        life = maxLife;
        attack = 6;
        defense = 0;
        exp = 8;
        knockBackPower = 5;

        solidArea.x = 4;
        solidArea.y = 4;
        solidArea.width = 40;
        solidArea.height = 44;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 48;
        attackArea.height = 48;
        motion1_duration = 40;
        motion2_duration = 85;

        getImage();
        getAttackImage();
    }

    public void getImage() {
        up1 = setup("/monster/soldier_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/monster/soldier_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/monster/soldier_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/monster/soldier_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/monster/soldier_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/monster/soldier_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/monster/soldier_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/monster/soldier_right_2", gp.tileSize, gp.tileSize);

    }

    public void getAttackImage() {
        attack_up1 = setup("/monster/soldier_attack_up1", gp.tileSize, gp.tileSize*2);
        attack_up2 = setup("/monster/soldier_attack_up2", gp.tileSize, gp.tileSize*2);
        attack_down1 = setup("/monster/soldier_attack_down1", gp.tileSize, gp.tileSize*2);
        attack_down2 = setup("/monster/soldier_attack_down2", gp.tileSize, gp.tileSize*2);
        attack_left1 = setup("/monster/soldier_attack_left1", gp.tileSize*2, gp.tileSize);
        attack_left2 = setup("/monster/soldier_attack_left2", gp.tileSize*2, gp.tileSize);
        attack_right1 = setup("/monster/soldier_attack_right1", gp.tileSize*2, gp.tileSize);
        attack_right2 = setup("/monster/soldier_attack_right2", gp.tileSize*2, gp.tileSize);
    }

    public void setAction() {
        if(onPath == true){

            // Check if it stops chasing
            checkStopChasingOrNot(gp.player, 15, 100);

            // search the direction to go
            searchPath(getGoalCol(gp.player),  getGoalRow(gp.player));

        }
        else {
            // check if it starts chasing
            checkStopChasingOrNot(gp.player, 5, 100);

            // get a random direction
            getRandomDirection();
        }

        // Check if it attacks
        if(attacking == false) {
            checkAttackOrNot(30, gp.tileSize*4, gp.tileSize);
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
