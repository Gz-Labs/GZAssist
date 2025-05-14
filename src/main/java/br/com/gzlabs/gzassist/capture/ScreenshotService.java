package br.com.gzlabs.gzassist.capture;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ScreenshotService {

    private final Robot robot;
    private final Rectangle screenBounds;

    public ScreenshotService() throws AWTException {
        robot = new Robot();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenBounds = new Rectangle(screenSize);
    }

    public BufferedImage captureScreen() {
        return robot.createScreenCapture(screenBounds);
    }
}

