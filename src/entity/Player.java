package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
import object.Key;
import object.ShieldWood;
import object.Spear;
import object.SwordNormal;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Player extends Entity{
    KeyHandler keyH;

    public final int screenX;//where we place the player
    public final int screenY;

    int standCounter = 0;
    boolean moving = false;
    int pixelCounter = 0;
    public boolean attackCanceled = false;
    public boolean lightUpdated = false;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp); // we call the constructor of the super class

        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);//we adjust the coordinates to the actual centre of the screen
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;
//        Attack Area
//        attackArea.width = 36;
//        attackArea.height = 36;

        setDefaultValues();
        getImage();
        getAttackImage();
        getGuardImage();
        setItems();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 30; //player's position on the world's map
        worldY = gp.tileSize * 26;

//        worldX = gp.tileSize*21; // the hut's position
//        worldY = gp.tileSize*31;
//        gp.currentMap = 1;
        defaultSpeed = 4;
        speed = defaultSpeed;
        direction = "down";

        // Player status
        level = 1;
        maxLife = 6;
        life = maxLife;
        maxAmmo = 4;
        ammo = maxAmmo;
//        stones = 10;
        strength = 1; // the more strength he has, the more damage he gives
        dexterity = 1; // the more dexterity he has, the less damage he recieves
        exp = 0;
        nextLevelExp = 5; // how much ex to level up
        coin = 500;
        currentWeapon = new SwordNormal(gp);
        currentShield = new ShieldWood(gp);
        projectile = new Spear(gp);
        attack = getAttack(); // the total attack values is decided by strength and weapon
        defense = getDefense(); // the total defense values is decided by dexterity and shield
    }

    public void setDefaultPositions() {
        worldX = gp.tileSize * 30; //player's position on the world's map
        worldY = gp.tileSize * 26;
        direction = "down";
    }

    public void restoreLifeAndAmmo() {
        life = maxLife;
        ammo = maxAmmo;
        invincible = false;
        transparent = false;
    }

    public void setItems() {
        inventory.clear();
        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(new Key(gp));
    }

    public int getAttack() {
        attackArea = currentWeapon.attackArea;
        motion1_duration = currentWeapon.motion1_duration;
        motion2_duration = currentWeapon.motion2_duration;
        return attack = strength * currentWeapon.attackValue;
    }

    public int getDefense() {
        return defense = dexterity * currentShield.defenseValue;
    }

    public void getImage() {
        up1 = setup("/player/BackWalking1", gp.tileSize, gp.tileSize);
        up2 = setup("/player/BackWalking2", gp.tileSize, gp.tileSize);
        down1 = setup("/player/FrontWalking1", gp.tileSize, gp.tileSize);
        down2 = setup("/player/FrontWalking2", gp.tileSize, gp.tileSize);
        left1 = setup("/player/Left", gp.tileSize, gp.tileSize);
        left2 = setup("/player/LeftWalking", gp.tileSize, gp.tileSize);
        right1 = setup("/player/Right", gp.tileSize, gp.tileSize);
        right2 = setup("/player/RightWalking", gp.tileSize, gp.tileSize);
    }

    public void getSleepingImage(BufferedImage image) {
        up1 = image;
        up2 = image;
        down1 = image;
        down2 = image;
        left1 = image;
        left2 = image;
        right1 = image;
        right2 = image;
    }

    public void getAttackImage() {
        if (currentWeapon.type == type_sword) {
            attack_up1 = setup("/player/attack_up1", gp.tileSize, gp.tileSize*2);
            attack_up2 = setup("/player/attack_up2", gp.tileSize, gp.tileSize*2);
            attack_down1 = setup("/player/attack_down1", gp.tileSize, gp.tileSize*2);
            attack_down2 = setup("/player/attack_down2", gp.tileSize, gp.tileSize*2);
            attack_left1 = setup("/player/attack_left1", gp.tileSize*2, gp.tileSize);
            attack_left2 = setup("/player/attack_left2", gp.tileSize*2, gp.tileSize);
            attack_right1 = setup("/player/attack_right1", gp.tileSize*2, gp.tileSize);
            attack_right2 = setup("/player/attack_right2", gp.tileSize*2, gp.tileSize);
        }

        if (currentWeapon.type == type_axe) {
            attack_up1 = setup("/player/axe_up1", gp.tileSize, gp.tileSize*2);
            attack_up2 = setup("/player/axe_up2", gp.tileSize, gp.tileSize*2);
            attack_down1 = setup("/player/axe_down1", gp.tileSize, gp.tileSize*2);
            attack_down2 = setup("/player/axe_down2", gp.tileSize, gp.tileSize*2);
            attack_left1 = setup("/player/axe_left1", gp.tileSize*2, gp.tileSize);
            attack_left2 = setup("/player/axe_left2", gp.tileSize*2, gp.tileSize);
            attack_right1 = setup("/player/axe_right1", gp.tileSize*2, gp.tileSize);
            attack_right2 = setup("/player/axe_right2", gp.tileSize*2, gp.tileSize);
        }
    }

    public void getGuardImage() {
        guardUp = setup("/player/guard_up", gp.tileSize, gp.tileSize);
        guardDown = setup("/player/guard_down", gp.tileSize, gp.tileSize);
        guardLeft = setup("/player/guard_left", gp.tileSize, gp.tileSize);
        guardRight = setup("/player/guard_right", gp.tileSize, gp.tileSize);
    }

    public void update() { //it's called 60 times per second

        if(knockBack == true) {
            collisionOn = false;
            gp.cChecker.checkTile(this);

            gp.cChecker.checkObject(this, true);

            gp.cChecker.checkEntity(this, gp.npc);

            gp.cChecker.checkEntity(this, gp.monster);

            // Check interactive tiles collision
            int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);

            if(collisionOn == true) {
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }
            else if(collisionOn == false) {
                switch (knockBackDirection) {
                    case "up":
                        worldY -= speed; // coordinates of the upper left corners are X:0, Y:0, X values increases tot the right, and Y values increases as they go down
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
            }
            knockBackCounter++;
            if(knockBackCounter == 5) {
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }
        }

        else if (attacking == true) {
            attacking();
        }
        else if(keyH.spacePressed == true) {
            guarding = true;
            guardCounter++;
        }

        else if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed  == true || keyH.enterPressed == true) { //the sprite counter changes only when a key is pressed
            if (keyH.upPressed == true) {
                direction = "up";
            } else if (keyH.downPressed == true) {
                direction = "down";
            } else if (keyH.leftPressed == true) {
                direction = "left";
            } else if (keyH.rightPressed == true) {
                direction = "right";
            }

            //CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // Check NPC collision
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            // Check monster collision
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            // Check interactive tiles collision
            int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);

            // Check event
            gp.eHandler.checkEvent();

            // If collision == false, player can move, without the second condition the player moves when pres enter
            if (collisionOn == false && gp.keyH.enterPressed == false) {
                switch (direction) {
                    case "up":
                        worldY -= speed; // coordinates of the upper left corners are X:0, Y:0, X values increases tot the right, and Y values increases as they go down
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
            }

            if (keyH.enterPressed == true && attackCanceled == false) {
//                gp.playerSE(7);
                attacking = true;
                spriteCounter = 0;
            }

            attackCanceled = false;

            gp.keyH.enterPressed = false; // starting dialogue when pressing enter
            guarding = false;
            guardCounter = 0;

            // every 12 frames the image changes
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1)
                    spriteNum = 2;
                else if (spriteNum == 2)
                    spriteNum = 1;
                spriteCounter = 0;
            }
        }
        else {
            standCounter++;
            if (standCounter == 20) {
                spriteNum = 1;
                standCounter = 0;
            }
            guarding = false;
            guardCounter = 0;
        }

//        pixelCounter += speed;
//
//        if(pixelCounter == 48) {
//            moving = false;
//            pixelCounter = 0;
//        }

        if(gp.keyH.shotKeyPressed == true && projectile.alive == false && shotAvailableCounter == 30 && projectile.haveResource(this) == true) { // you can't shoot a projectile if the previous one is still "alive", so only one at a time
            // Set default coordinates, direction and user
            projectile.set(worldX, worldY, direction, true, this);

            // Subtract the cost (ammo)
            projectile.subtractResource(this);

            // Check vacancy
            for(int i = 0; i < gp.projectile[1].length; i++) {
                if(gp.projectile[gp.currentMap][i] == null) {
                    gp.projectile[gp.currentMap][i] = projectile;
                    break;
                }
            }

            shotAvailableCounter = 0;
//            gp.playSE(10);
        }

        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 60) { // One second
                invincible = false;
                transparent = false;
                invincibleCounter = 0;

            }
        }

        if(shotAvailableCounter < 30) { // if you shoot a Spear, you can't shoot another one for the next 30 frames
            shotAvailableCounter++;
        }

        if(life > maxLife) {
            life = maxLife;
        }

        if(ammo > maxAmmo) {
            ammo = maxAmmo;
        }
//        comment out to make him immortal
        if(life <= 0) {
            gp.gameState = gp.gameOverState;
            gp.ui.commandNum = -1;
//            gp.stopMusic();
//            gp.playSE(12);
        }
    }

    public void pickUpObject(int i) {
        if (i != 999) {
            // Pickup only items
            if (gp.obj[gp.currentMap][i].type == type_pickupOnly) {
                gp.obj[gp.currentMap][i].use(this);
                gp.obj[gp.currentMap][i] = null;
            }
            // obstacle
            else if(gp.obj[gp.currentMap][i].type == type_obstacle) {
                if(keyH.enterPressed == true) {
                    attackCanceled = true;
                    gp.obj[gp.currentMap][i].interact();
                }
            }
            // Inventory items
            else {
                String text;
                if (canObtainItem(gp.obj[gp.currentMap][i]) == true) {
//                gp.playSE(1);
                    text = "Got a " + gp.obj[gp.currentMap][i].name + "!";
                } else {
                    text = "You can't carry any more!";
                }
                gp.ui.addMessage(text);
                gp.obj[gp.currentMap][i] = null;
            }
        }
    }

    public void interactNPC(int i) {
        if (gp.keyH.enterPressed == true) {
            if (i != 999) {
                attackCanceled = true;
                gp.gameState = gp.dialogueState;
                gp.npc[gp.currentMap][i].speak();
            }
        }
    }

    public void contactMonster(int i) {
        if (i != 999) {
            if (invincible == false && gp.monster[gp.currentMap][i].dying == false) { // This draws only a part of the life instead of all of it like before
                // gp.playSE(6);

                int damage = gp.monster[gp.currentMap][i].attack - defense;
                if (damage < 1) {
                    damage = 1;
                }
                life -= damage;
                invincible = true;
                transparent = true;
            }
        }
    }

    public void damageMonster(int i, Entity attacker, int attack, int knockBackPower) {
        if (i != 999) {
            if (gp.monster[gp.currentMap][i].invincible == false) {
//                gp.playSE(5);

                if(knockBackPower > 0) {
                    setKnockBack(gp.monster[gp.currentMap][i], attacker, knockBackPower);
                }

                if(gp.monster[gp.currentMap][i].offBalance == true) {
                    attack *= 5;
                }

                int damage = attack - gp.monster[gp.currentMap][i].defense;
                if (damage < 0) {
                    damage = 0;
                }

                gp.monster[gp.currentMap][i].life -= damage;
                gp.ui.addMessage(damage + " damage!");

                gp.monster[gp.currentMap][i].invincible = true;
                gp.monster[gp.currentMap][i].damageReaction();

                if (gp.monster[gp.currentMap][i].life <= 0) {
                    gp.monster[gp.currentMap][i].dying = true;
                    gp.ui.addMessage("Killed the " + gp.monster[gp.currentMap][i].name + "!");
                    gp.ui.addMessage("Exp + " + gp.monster[gp.currentMap][i].exp);
                    exp += gp.monster[gp.currentMap][i].exp;
                    checkLevelUp();
                }
            }
        }
    }

    public void damageProjectile(int i) {
        if(i != 999) {
            Entity projectile = gp.projectile[gp.currentMap][i];
            projectile.alive = false;
            generateParticle(projectile, projectile);
        }
    }

    public void checkLevelUp() {
        if (exp >= nextLevelExp) {
            level++;
            nextLevelExp = nextLevelExp*2;
            maxLife += 2;
            strength++;
            dexterity++;
            attack = getAttack();
            defense = getDefense();

            // gp.playSE(8);

            gp.gameState = gp.dialogueState;
            gp.ui.currentDialogue = "You are level " + level + "!\n" + "You are stronger!";

        }
    }

    public void damageInteractiveTile(int i) {
        if (i != 999 && gp.iTile[gp.currentMap][i].destructible == true && gp.iTile[gp.currentMap][i].isCorrectItem(this) == true && gp.iTile[gp.currentMap][i].invincible == false) {
            gp.iTile[gp.currentMap][i].playSE();
            gp.iTile[gp.currentMap][i].life--;
            gp.iTile[gp.currentMap][i].invincible = true;
            generateParticle(gp.iTile[gp.currentMap][i], gp.iTile[gp.currentMap][i]);

            if (gp.iTile[gp.currentMap][i].life == 0) {
                gp.iTile[gp.currentMap][i] = gp.iTile[gp.currentMap][i].getDistroyedForm();
            }
        }
    }

    public void selectItem(){
        int itemIndex = gp.ui.getItemIndexOnSlot(gp.ui.playerSlotCol, gp.ui.playerSlotRow);
        if (itemIndex < inventory.size()) {
            Entity selectedItem = inventory.get(itemIndex);

            if (selectedItem.type == type_sword || selectedItem.type == type_axe) {
                currentWeapon = selectedItem;
                attack = getAttack();
                getAttackImage();
            }

            if (selectedItem.type == type_shield) {
                currentShield = selectedItem;
                defense = getDefense();
            }

            if(selectedItem.type == type_light) {
                if(currentLight == selectedItem) {
                    currentLight = null;
                }
                else {
                    currentLight = selectedItem;
                }
                lightUpdated = true;
            }

            if (selectedItem.type == type_consumable) {
                if(selectedItem.use(this) == true) {
                    if(selectedItem.amount > 1) {
                        selectedItem.amount--;
                    }
                    else inventory.remove(itemIndex);
                }
            }
        }
    }

    public int searchItemInInventory(String itemName) {
        int itemIndex = 999;
        for (int i = 0; i < inventory.size(); i++) {
            if(inventory.get(i).name.equals(itemName)) {
                itemIndex = i;
                break;
            }
        }
        return itemIndex;
    }

    public boolean canObtainItem(Entity item) {
        boolean canObtain = false;
        // Check if stackable
        if(item.stackable == true) {
            int index = searchItemInInventory(item.name);
            if(index != 999) {
                inventory.get(index).amount++;
                canObtain = true;
            }
            else { // New Item so need to check vacancy
                if(inventory.size() != maxInventorySize) {
                    inventory.add(item);
                    canObtain = true;
                }
            }
        }
        else { // not stackable, check vacancy
            if(inventory.size() != maxInventorySize) {
                inventory.add(item);
                canObtain = true;
            }
        }
        return canObtain;
    }

    public void draw(Graphics2D g2) {
//        g2.setColor(Color.white); // sets a color to use for drawing
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize); // x, y, width, height

        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch(direction) {
            case "up":
                if (attacking == false) {
                    if(spriteNum == 1)
                        image = up1;
                    if(spriteNum == 2)
                        image = up2;
                }
                if (attacking == true) {
                    tempScreenY = screenY - gp.tileSize; // so it doesn't slide
                    if(spriteNum == 1)
                        image = attack_up1;
                    if(spriteNum == 2)
                        image = attack_up2;
                }
                if(guarding == true) {
                    image = guardUp;
                }
                break;
            case "down":
                if (attacking == false) {
                    if(spriteNum == 1)
                        image = down1;
                    if(spriteNum == 2)
                        image = down2;
                }
                if (attacking == true) {
                    if (spriteNum == 1)
                        image = attack_down1;
                    if (spriteNum == 2)
                        image = attack_down2;
                }
                if(guarding == true) {
                    image = guardDown;
                }
                break;
            case "left":
                if (attacking == false) {
                    if(spriteNum == 1)
                        image = left1;
                    if(spriteNum == 2)
                        image = left2;
                }
                if (attacking == true) {
                    tempScreenX = screenX - gp.tileSize;
                    if(spriteNum == 1)
                        image = attack_left1;
                    if(spriteNum == 2)
                        image = attack_left2;
                }
                if(guarding == true) {
                    image = guardLeft;
                }
                break;
            case "right":
                if (attacking == false) {
                    if(spriteNum == 1)
                        image = right1;
                    if(spriteNum == 2)
                        image = right2;
                }
                if (attacking == true) {
                    if(spriteNum == 1)
                        image = attack_right1;
                    if(spriteNum == 2)
                        image = attack_right2;
                }
                if(guarding == true) {
                    image = guardRight;
                }
                break;
        }

        if (transparent == true) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f)); // 70% trasparent
        }

        g2.drawImage(image, tempScreenX, tempScreenY, null);

        // Reset Alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f)); // 70% trasparent

        // Draw the collision rectangle of the player
//        g2.setColor(Color.red);
//        g2.drawRect(screenX + solidArea.x, screenY + + solidArea.y, solidArea.width, solidArea.height);
        // Debug
//        g2.setFont(new Font("Arial", Font.PLAIN, 26));
//        g2.setColor(Color.white);
//        g2.drawString("Invincible: " + invincibleCounter, 10, 400);
    }
}
















