package br.com.gzlabs.gzassist;

import br.com.gzlabs.gzassist.application.AnswerService;
import br.com.gzlabs.gzassist.application.AppFactory;
import br.com.gzlabs.gzassist.presentation.HomeController;
import br.com.gzlabs.gzassist.presentation.OverlayController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static javafx.scene.paint.Color.web;

public class Main extends Application {

    private AnswerService answerService;
    private ExecutorService executor;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("presentation/home-view.fxml"));
        Scene scene = new Scene(loader.load(), 430, 680, web("#181a1b"));
        scene.getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("presentation/style.css")).toExternalForm()
        );
        stage.setScene(scene);
        stage.setTitle("GZAssist");
        stage.setResizable(false);
        stage.setMaximized(false);
        stage.initStyle(StageStyle.DECORATED);
        stage.show();

        FXMLLoader overlayLoader = new FXMLLoader(Main.class.getResource("presentation/overlay-view.fxml"));
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
