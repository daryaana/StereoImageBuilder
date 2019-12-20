import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainPanel extends JPanel {
    BufferedImage firstImage = null;
    BufferedImage secondImage = null;
    BufferedImage thirdImage = null;
    public void blackWhiteFilter() {
        thirdImage = blackWhite(secondImage);
        repaint();
    }
    public BufferedImage blackWhite(BufferedImage image) {

        if (secondImage == null) {
            image = firstImage;
        }
        thirdImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB/*image.getType()*/);

        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                double newPixel = (new Color(image.getRGB(i, j)).getRed() * 0.299) +
                        (new Color(image.getRGB(i, j)).getBlue() * 0.144) +
                        (new Color(image.getRGB(i, j)).getGreen() * 0.587);
                if (newPixel > 255) {
                    newPixel = 255;
                } else {
                    newPixel = newPixel;
                }
                newPixel = new Color((int) newPixel, (int) newPixel, (int) newPixel).getRGB();
                thirdImage.setRGB(i, j, (int) newPixel);
                return thirdImage;
            }
        }

    }

}
