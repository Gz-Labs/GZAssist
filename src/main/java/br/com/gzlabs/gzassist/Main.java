package br.com.gzlabs.gzassist;

import br.com.gzlabs.gzassist.core.OpenAiClient;
import br.com.gzlabs.gzassist.core.AnswerCoordinator;
import br.com.gzlabs.gzassist.core.ScreenshotCapture;
import br.com.gzlabs.gzassist.ui.OverlayPopup;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private AnswerCoordinator coordinator;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("ui/home-view.fxml"));
        stage.setScene(new Scene(loader.load(), 320, 240));
        stage.setTitle("Hello!");
        stage.show();

        ScreenshotCapture screenshotCapture = new ScreenshotCapture();
        OpenAiClient ai = new OpenAiClient();
        OverlayPopup overlay = new OverlayPopup(stage);

        coordinator = new AnswerCoordinator(screenshotCapture, ai, overlay);
    }

    @Override
    public void stop() {
        if (coordinator != null) {
            coordinator.close();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
