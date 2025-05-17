package br.com.gzlabs.gzassist.adapters;

import br.com.gzlabs.gzassist.core.ScreenCapturer;
import br.com.gzlabs.gzassist.errors.CaptureException;

import java.awt.*;
import java.awt.image.BufferedImage;

public final class RobotScreenCapturer implements ScreenCapturer {

    private final Robot robot;
    private final Rectangle screenBounds;

    public RobotScreenCapturer() throws AWTException {
        this.robot = new Robot();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.screenBounds = new Rectangle(screenSize);
    }

    @Override
    public BufferedImage capture() throws CaptureException {
        try {
            return robot.createScreenCapture(screenBounds);
        } catch (Exception e) {
            throw new CaptureException("Erro ao capturar a tela", e);
        }
    }
}
