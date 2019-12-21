import org.junit.Test;

import static org.junit.Assert.*;

public class MainPanelTest {
    MainPanel panel = new MainPanel();
    @Test
    public void checkColor() {
        assertEquals(255, panel.checkColor(555));
        assertEquals(0, panel.checkColor(-555));
    }
}