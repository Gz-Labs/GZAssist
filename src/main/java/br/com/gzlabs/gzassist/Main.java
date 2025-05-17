package br.com.gzlabs.gzassist;

import br.com.gzlabs.gzassist.ai.OpenAiClient;
import br.com.gzlabs.gzassist.app.GZAssistManager;
import br.com.gzlabs.gzassist.capture.ScreenshotCapture;
import br.com.gzlabs.gzassist.ui.OverlayPopup;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private GZAssistManager manager;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("ui/home-view.fxml"));
        stage.setScene(new Scene(loader.load(), 320, 240));
        stage.setTitle("Hello!");
        stage.show();

        ScreenshotCapture screenshotCapture = new ScreenshotCapture();
        OpenAiClient ai = new OpenAiClient();
        OverlayPopup overlay = new OverlayPopup(stage);

        manager = new GZAssistManager(screenshotCapture, ai, overlay);
    }

    @Override
    public void stop() {
        if (manager != null) {
            manager.close();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
