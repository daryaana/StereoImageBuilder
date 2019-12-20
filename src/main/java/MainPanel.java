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
    private double scale;
    Rectangle selector;
    Rectangle bounds;
    public void loadFile(File file) {
        try {
            firstImage = ImageIO.read(new File(file.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int imageHeight = firstImage.getHeight();
        int imageWidth = firstImage.getWidth();
        double scaleWidth = imageWidth / (double) imageWidth;
        double scaleHeight = imageHeight / (double) imageHeight;
        scale = scaleHeight < scaleWidth ? scaleHeight : scaleWidth;

        if (scale >= 1.0) {
            scale = 1.0;
        }

        if (scale == 1.0) {
            selector = new Rectangle(0, 0, imageWidth, imageHeight);
        } else {
            selector = new Rectangle(0, 0, (int) (350 * scale), (int) (350 * scale));
        }

        int height = (int) (imageHeight * scale);
        int width = (int) (imageWidth * scale);

        bounds = new Rectangle(10, 10, width, height);

        secondImage = null;
        thirdImage = null;
        repaint();
    }
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
