package br.com.gzlabs.gzassist;

import br.com.gzlabs.gzassist.application.AnswerService;
import br.com.gzlabs.gzassist.application.AppFactory;
import br.com.gzlabs.gzassist.presentation.OverlayPopup;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main extends Application {

    private AnswerService answerService;
    private ExecutorService executor;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("presentation/home-view.fxml"));
        stage.setScene(new Scene(loader.load(), 320, 240));
        stage.setTitle("GZAssist");
        stage.show();

        OverlayPopup overlay = new OverlayPopup(stage);

        executor = Executors.newFixedThreadPool(2);
        answerService = AppFactory.createAnswerService(overlay::handleUiEvent, executor);
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
