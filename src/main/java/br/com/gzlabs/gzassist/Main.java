package br.com.gzlabs.gzassist;

import br.com.gzlabs.gzassist.ai.AiService;
import br.com.gzlabs.gzassist.capture.ScreenshotService;
import br.com.gzlabs.gzassist.core.ScreenshotHotkeyManager;
import br.com.gzlabs.gzassist.ui.OverlayService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private ScreenshotHotkeyManager manager;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        stage.setScene(new Scene(loader.load(), 320, 240));
        stage.setTitle("Hello!");
        stage.show();

        ScreenshotService screenshotService = new ScreenshotService();
        AiService ai = new AiService();
        OverlayService overlay = new OverlayService(stage);

        manager = new ScreenshotHotkeyManager(screenshotService, ai, overlay);
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
