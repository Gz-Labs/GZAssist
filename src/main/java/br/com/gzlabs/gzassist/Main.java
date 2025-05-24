package br.com.gzlabs.gzassist;

import br.com.gzlabs.gzassist.application.AnswerService;
import br.com.gzlabs.gzassist.application.AppFactory;
import br.com.gzlabs.gzassist.desktop.HomeController;
import br.com.gzlabs.gzassist.desktop.OverlayController;
import br.com.gzlabs.gzassist.util.ThemeManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main extends Application {

    private AnswerService answerService;
    private ExecutorService executor;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/desktop/home/home-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 430, 680);
        ThemeManager.applyTheme(scene, root);
        stage.setScene(scene);
        stage.setTitle("GZAssist");
        stage.setResizable(false);
        stage.setMaximized(false);
        stage.initStyle(StageStyle.DECORATED);
        stage.show();

        FXMLLoader overlayLoader = new FXMLLoader(Main.class.getResource("/desktop/overlay/overlay-view.fxml"));
        overlayLoader.load();
        OverlayController overlay = overlayLoader.getController();
        overlay.init(stage);

        HomeController homeController = loader.getController();
        executor = Executors.newFixedThreadPool(2);
        answerService = AppFactory.createAnswerService(overlay::handleUiEvent, executor, homeController::getSelectedMode);
    }

    @Override
    public void stop() {
        if (answerService != null) answerService.close();
        if (executor != null) executor.shutdown();
    }

    public static void main(String[] args) {
        launch();
    }
}
