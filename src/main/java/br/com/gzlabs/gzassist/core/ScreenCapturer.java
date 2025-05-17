package br.com.gzlabs.gzassist.core;

import java.awt.image.BufferedImage;
import br.com.gzlabs.gzassist.errors.CaptureException;

public interface ScreenCapturer {
    BufferedImage capture() throws CaptureException;
}
