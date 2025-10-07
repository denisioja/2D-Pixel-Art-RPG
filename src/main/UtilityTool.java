package main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UtilityTool {
    public BufferedImage scaleImage(BufferedImage original, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaledImage.createGraphics(); // Creates a Graphics2D, which can be used to draw into this bufferedImage
        g2.drawImage(original, 0, 0, width, height, null); // Draw tile[0].image into the scaledImage (BufferedImage) that this Graphics2D is linked to.

        return scaledImage;
    }
}
