package environment;

import main.GamePanel;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Lighting {
    GamePanel gp;
    BufferedImage darknessFilter;
    public int dayCounter;
    public float filterAlpha = 0f;

    // Day State
    public final int day = 0;
    public final int dusk = 1;
    public final int night = 2;
    public final int dawn = 3;
    public int dayState = day;

    public Lighting(GamePanel gp) {
        this.gp = gp;
        setLightSource();
    }

    public void setLightSource() {
        // Create a buffered image
        darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D) darknessFilter.getGraphics();

        if(gp.player.currentLight == null) {
            g2.setColor(new Color(0, 0, 0, 0.98f));
        }
        else {
            //Get the center x and y of the light circle
            int centerX = gp.player.screenX + (gp.tileSize)/2;
            int centerY = gp.player.screenY + (gp.tileSize)/2;

            // Create a gradation effect within the light circle
            Color color[] = new Color[5];
            float fraction[] = new float[5];

            color[0] = new Color(0, 0, 0, 0f);
            color[1] = new Color(0, 0, 0, 0.25f);
            color[2] = new Color(0, 0, 0, 0.5f);
            color[3] = new Color(0, 0, 0, 0.75f);
            color[4] = new Color(0, 0, 0, 0.98f);

            fraction[0] = 0f;
            fraction[1] = 0.25f;
            fraction[2] = 0.5f;
            fraction[3] = 0.75f;
            fraction[4] = 1f;

            // Create a gradation paint settings for the light circle
            RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY, gp.player.currentLight.lightRadius, fraction, color);

            // Set the gradient data on g2
            g2.setPaint(gPaint);
        }

        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.dispose();
    }

    public void update() {
        if(gp.player.lightUpdated == true) {
            setLightSource();
            gp.player.lightUpdated = false;
        }

        // Check the state of the day
        if(dayState == day) {
            dayCounter++;

            if(dayCounter > 6000) { // around 10 seconds
                dayState = dusk;
                dayCounter = 0;
            }
        }

        if(dayState == dusk) {
            filterAlpha += 0.001f;

            if(filterAlpha > 1f) {
                filterAlpha = 1f;
                dayState = night;
            }
        }
        if(dayState == night) {
            dayCounter ++;
            if(dayCounter > 6000) { // 36000 around 10 min ==>
                dayState = dawn;
                dayCounter = 0;
            }
        }
        if(dayState == dawn) {
            filterAlpha -= 0.001f;

            if(filterAlpha < 0f) {
                filterAlpha = 0;
                dayState = day;
            }
        }
    }

    public void draw(Graphics2D g2) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, filterAlpha));
        g2.drawImage(darknessFilter, 0, 0, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        // Debug
        String situation = "";
        switch(dayState) {
            case day:
                situation = "Day";
                break;
            case dawn:
                situation = "Dawn";
                break;
            case night:
                situation = "Night";
                break;
            case dusk:
                situation = "Dusk";
                break;
        }
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(50f));
        g2.drawString(situation, 800, 500);
    }
}































