package br.com.gzlabs.gzassist.capture;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ScreenshotCapture {

    private final Robot robot;
    private final Rectangle screenBounds;

    public ScreenshotCapture() throws AWTException {
        robot = new Robot();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenBounds = new Rectangle(screenSize);
    }

    public BufferedImage captureScreen() {
        return robot.createScreenCapture(screenBounds);
    }
}

