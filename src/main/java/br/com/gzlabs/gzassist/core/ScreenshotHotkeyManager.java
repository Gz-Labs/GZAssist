package br.com.gzlabs.gzassist.core;

import br.com.gzlabs.gzassist.ai.AiService;
import br.com.gzlabs.gzassist.capture.ScreenshotService;
import br.com.gzlabs.gzassist.hotkey.HotkeyService;
import br.com.gzlabs.gzassist.ui.OverlayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.CompletableFuture;

public class ScreenshotHotkeyManager implements AutoCloseable {

    private static final Logger logger = LoggerFactory.getLogger(ScreenshotHotkeyManager.class);

    private final ScreenshotService screenshotService;
    private final HotkeyService hotkeyService;
    private final AiService ai;
    private final OverlayService overlay;

    public ScreenshotHotkeyManager(ScreenshotService screenshotService, AiService ai, OverlayService overlay) {
        this.screenshotService = screenshotService;
        this.ai = ai;
        this.overlay = overlay;
        this.hotkeyService = new HotkeyService(this::onHotkeyPressed);
    }

    private void onHotkeyPressed() {
        try {
            BufferedImage img = screenshotService.captureScreen();
            Rectangle bounds = new Rectangle(0, 0, img.getWidth(), img.getHeight());
            logger.info("Screenshot capturada: {}x{}", img.getWidth(), img.getHeight());

            overlay.showLoading();

            CompletableFuture.supplyAsync(() -> ai.solve(bounds))
                    .thenAccept(opt ->
                            opt.ifPresentOrElse(
                                    overlay::show,
                                    () -> overlay.showError("Sem resposta da IA")
                            ));
        } catch (Exception e) {
            logger.error("Erro ao capturar tela", e);
            overlay.showError("Falha ao capturar tela");
        }
    }

    @Override
    public void close() {
        hotkeyService.close();
    }
}
