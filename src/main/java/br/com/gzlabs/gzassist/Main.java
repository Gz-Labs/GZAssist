package br.com.gzlabs.gzassist;

import br.com.gzlabs.gzassist.core.AnswerService;
import br.com.gzlabs.gzassist.infra.GlobalHotkeyBinder;
import br.com.gzlabs.gzassist.infra.OpenAiAnswerProvider;
import br.com.gzlabs.gzassist.infra.PromptTemplates;
import br.com.gzlabs.gzassist.infra.RobotScreenCapturer;
import br.com.gzlabs.gzassist.ui.OverlayPopup;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main extends Application {

    private AnswerService coordinator;
    private ExecutorService executor;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("ui/home-view.fxml"));
        stage.setScene(new Scene(loader.load(), 320, 240));
        stage.setTitle("GZAssist");
        stage.show();

        executor = Executors.newFixedThreadPool(2);
        OverlayPopup overlay = new OverlayPopup(stage);

        coordinator = new AnswerService(
                new RobotScreenCapturer(),
                new OpenAiAnswerProvider(
                        OpenAIOkHttpClient.fromEnv(),
                        new PromptTemplates()
                ),
                new GlobalHotkeyBinder(),
                executor,
                overlay::handleUiEvent
        );
    }

    @Override
    public void stop() {
        if (coordinator != null) coordinator.close();
        if (executor != null) executor.shutdown();
    }

    public static void main(String[] args) {
        launch();
    }
}
