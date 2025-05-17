package br.com.gzlabs.gzassist.application;

import br.com.gzlabs.gzassist.adapters.GlobalHotkeyBinder;
import br.com.gzlabs.gzassist.adapters.OpenAiAnswerProvider;
import br.com.gzlabs.gzassist.adapters.RobotScreenCapturer;
import br.com.gzlabs.gzassist.errors.HotkeyException;
import br.com.gzlabs.gzassist.presentation.UiEvent;
import br.com.gzlabs.gzassist.util.PromptTemplates;
import com.openai.client.okhttp.OpenAIOkHttpClient;

import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

public class AppFactory {

    private AppFactory() {}

    public static AnswerService createAnswerService(Consumer<UiEvent> uiHandler, ExecutorService executor) throws AWTException, HotkeyException {
        return new AnswerService(
                new RobotScreenCapturer(),
                new OpenAiAnswerProvider(
                        OpenAIOkHttpClient.fromEnv(),
                        new PromptTemplates()
                ),
                new GlobalHotkeyBinder(),
                executor,
                uiHandler
        );
    }
}
