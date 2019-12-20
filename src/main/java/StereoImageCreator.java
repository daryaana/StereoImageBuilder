import java.awt.image.BufferedImage;

public class StereoImageCreator {
    public int getRed(int x,int y, BufferedImage image) {
        int rgb = image.getRGB(x,y);
        int red = (rgb >> 16) & 0xFF;
        return red;
    }
    public BufferedImage generateStereoPicture(BufferedImage bitmapMask, BufferedImage stereoImg)
    {
// Переводим маску в массив сдвигов
        int w = bitmapMask.getWidth();
        int h = bitmapMask.getHeight();
        int[][] mask = new int[w][];
        for (int x = 0; x < w; x++)
        {
            mask[x] = new int[h];
            for (int y = 0; y < h; y++) {
                mask[x][y] = getRed(x,y,bitmapMask);
            }
        }

// Cоздаем фон
        int s = 100;

// Сдвигаем каждый пиксел
        for (int y = 0; y < h; y++)
            for (int x = 0; x < w; x++)
                if (mask[x][y] > 0)
                {
                    int pixel = stereoImg.getRGB(x + mask[x][y], y);
                    for (int i = x + s; i < w + s; i += s)
                        stereoImg.setRGB(i, y, pixel);
                }

        return stereoImg;
    }
}
