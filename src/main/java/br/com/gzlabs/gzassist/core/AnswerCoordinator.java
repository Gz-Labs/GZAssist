package br.com.gzlabs.gzassist.core;

import br.com.gzlabs.gzassist.errors.AiException;
import br.com.gzlabs.gzassist.errors.CaptureException;
import br.com.gzlabs.gzassist.errors.HotkeyException;
import br.com.gzlabs.gzassist.ui.UiEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.BufferedImage;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

public final class AnswerCoordinator implements AutoCloseable {

    private static final Logger LOG = LoggerFactory.getLogger(AnswerCoordinator.class);

    private final ScreenCapturer capturer;
    private final AnswerProvider provider;
    private final HotkeyBinder hotkey;
    private final Executor executor;
    private final Consumer<UiEvent> uiHandler;

    public AnswerCoordinator(ScreenCapturer capturer,
                             AnswerProvider provider,
                             HotkeyBinder hotkey,
                             Executor executor,
                             Consumer<UiEvent> uiHandler) throws HotkeyException {
        this.capturer = capturer;
        this.provider = provider;
        this.hotkey = hotkey;
        this.executor = executor;
        this.uiHandler = uiHandler;

        this.hotkey.register(this::onHotkeyTriggered);
    }

    private void onHotkeyTriggered() {
        uiHandler.accept(UiEvent.loading());

        CompletableFuture
                .supplyAsync(this::processScreenshot, executor)
                .thenAccept(uiHandler)
                .exceptionally(ex -> {
                    LOG.error("Erro inesperado durante o processamento", ex);
                    uiHandler.accept(UiEvent.error("Erro inesperado: " + ex.getMessage()));
                    return null;
                });
    }

    private UiEvent processScreenshot() {
        try {
            BufferedImage screenshot = capturer.capture();
            LOG.info("Screenshot capturada com sucesso ({}x{})", screenshot.getWidth(), screenshot.getHeight());

            Optional<String> response = provider.answer(screenshot);
            return response.map(UiEvent::success)
                    .orElseGet(() -> UiEvent.error("Sem resposta da IA"));

        } catch (CaptureException e) {
            LOG.warn("Falha na captura de tela", e);
            return UiEvent.error("Erro ao capturar a tela");
        } catch (AiException e) {
            LOG.warn("Falha ao consultar IA", e);
            return UiEvent.error("Erro ao obter resposta da IA");
        }
    }

    @Override
    public void close() {
        try {
            hotkey.close();
        } catch (Exception e) {
            LOG.warn("Erro ao fechar recursos do hotkey", e);
        }
    }
}
