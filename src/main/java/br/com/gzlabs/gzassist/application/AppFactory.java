package br.com.gzlabs.gzassist.application;

import br.com.gzlabs.gzassist.adapters.GlobalHotkeyBinder;
import br.com.gzlabs.gzassist.adapters.OpenAiAnswerProvider;
import br.com.gzlabs.gzassist.adapters.RobotScreenCapturer;
import br.com.gzlabs.gzassist.config.AppConfig;
import br.com.gzlabs.gzassist.core.Mode;
import br.com.gzlabs.gzassist.errors.HotkeyException;
import br.com.gzlabs.gzassist.presentation.UiEvent;
import br.com.gzlabs.gzassist.util.PromptTemplates;
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.services.blocking.chat.ChatCompletionService;

import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class AppFactory {

    private AppFactory() {
    }

    public static AnswerService createAnswerService(Consumer<UiEvent> uiHandler,
                                                    ExecutorService executor,
                                                    Supplier<Mode> modeSupplier)
            throws AWTException, HotkeyException {
        Supplier<ChatCompletionService> chatSupplier = () -> {
            String apiKey = AppConfig.getApiKey();
            OpenAIClient client = OpenAIOkHttpClient.builder()
                    .apiKey(apiKey)
                    .build();
            return client.chat().completions();
        };

        return new AnswerService(
                new RobotScreenCapturer(),
                new OpenAiAnswerProvider(chatSupplier, new PromptTemplates()),
                new GlobalHotkeyBinder(),
                executor,
                uiHandler,
                modeSupplier
        );
    }
}