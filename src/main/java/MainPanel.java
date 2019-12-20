import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
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
    boolean visible;
    MainPanel() {


    }
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
            }
        }

return thirdImage;
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        drawDashedLine(g, 10, 10, 360, 10);//-
        drawDashedLine(g, 360, 10, 360, 360);//|
        drawDashedLine(g, 360, 360, 10, 360);
        drawDashedLine(g, 10, 360, 10, 10);


        drawDashedLine(g, 370, 10, 720, 10);
        drawDashedLine(g, 720, 10, 720, 360);
        drawDashedLine(g, 720, 360, 370, 360);
        drawDashedLine(g, 370, 360, 370, 10);

        drawDashedLine(g, 730, 10, 1080, 10);
        drawDashedLine(g, 1080, 10, 1080, 360);
        drawDashedLine(g, 1080, 360, 730, 360);
        drawDashedLine(g, 730, 360, 730, 10);


        if (firstImage != null) {
            if (firstImage.getWidth() > 350 || firstImage.getHeight() > 350) {
                g.drawImage(firstImage, 10, 10, 350, 350, null);
            } else {
                g.drawImage(firstImage, 10, 10, null);
            }
        }

        if (secondImage != null) {
            if (secondImage.getWidth() > 350 || secondImage.getHeight() > 350) {
                g.drawImage(secondImage, 370, 10, 350, 350, null);
            } else {
                g.drawImage(secondImage, 370, 10, null);
            }
        }

        if (thirdImage != null) {
            if (thirdImage.getWidth() > 350 || thirdImage.getHeight() > 350) {
                g.drawImage(thirdImage, 730, 10, 350, 350, null);
            } else {
                g.drawImage(thirdImage, 730, 10, null);
            }
        }
        g.setXORMode(Color.WHITE);
        Graphics2D g2d = (Graphics2D) g.create();
        Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{3}, 0);
        g2d.setStroke(dashed);
        if (visible) {
            g2d.draw(selector);
        }

    }
    private void drawDashedLine(Graphics g, int x1, int y1, int x2, int y2) {
        Graphics2D graphics2D = (Graphics2D) g.create();
        Stroke dashed = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{3}, 0);
        graphics2D.setStroke(dashed);
        graphics2D.drawLine(x1, y1, x2, y2);
    }
}
