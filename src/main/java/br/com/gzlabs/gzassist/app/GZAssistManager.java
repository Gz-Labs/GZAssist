package br.com.gzlabs.gzassist.app;

import br.com.gzlabs.gzassist.ai.OpenAiClient;
import br.com.gzlabs.gzassist.capture.ScreenshotCapture;
import br.com.gzlabs.gzassist.hotkey.HotkeyListener;
import br.com.gzlabs.gzassist.ui.OverlayPopup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.BufferedImage;
import java.util.concurrent.CompletableFuture;

public class GZAssistManager implements AutoCloseable {

    private static final Logger logger = LoggerFactory.getLogger(GZAssistManager.class);

    private final ScreenshotCapture screenshotCapture;
    private final HotkeyListener hotkeyListener;
    private final OpenAiClient ai;
    private final OverlayPopup overlay;

    public GZAssistManager(ScreenshotCapture screenshotCapture, OpenAiClient ai, OverlayPopup overlay) {
        this.screenshotCapture = screenshotCapture;
        this.ai = ai;
        this.overlay = overlay;
        this.hotkeyListener = new HotkeyListener(this::onHotkeyPressed);
    }

    private void onHotkeyPressed() {
        try {
            BufferedImage img = screenshotCapture.captureScreen();
            logger.info("Screenshot capturada: {}x{}", img.getWidth(), img.getHeight());

            overlay.showLoading();

            CompletableFuture.supplyAsync(() -> ai.solve(img))
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
        hotkeyListener.close();
    }
}
