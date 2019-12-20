
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class StereoImageCreatorTest {
    StereoImageCreator creator = new StereoImageCreator();


    @Test
    public void getRed() {

        BufferedImage pattern = null;
        try {
            pattern = ImageIO.read(new File("src/main/resources/images/circle_300x300.jpg"));
        } catch (IOException e) {
        }
        assertEquals(0, creator.getRed(0,0,pattern));
    }
}