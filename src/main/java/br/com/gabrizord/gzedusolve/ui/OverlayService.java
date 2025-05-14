package br.com.gabrizord.gzedusolve.ui;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;
import javafx.stage.Screen;
import javafx.stage.Window;
import javafx.util.Duration;

public final class OverlayService {

    private static final int AUTO_CLOSE_SECONDS = 5;
    private static final double PANEL_WIDTH = 450;
    private static final double MARGIN = 20;

    private final Window ownerWindow;
    private final Popup popup;
    private final StackPane root;
    private final Label output;
    private final ProgressIndicator spinner;
    private final Timeline autoClose;
    private final FadeTransition fadeIn;
    private final FadeTransition fadeOut;

    public OverlayService(Window ownerWindow) {
        this.ownerWindow = ownerWindow;

        output = new Label();
        output.setWrapText(true);
        output.setTextAlignment(TextAlignment.LEFT);
        output.setAlignment(Pos.TOP_LEFT);
        output.setVisible(false);
        output.setStyle("""
                    -fx-text-fill: #ECECF1;
                    -fx-font-size: 15px;
                    -fx-font-family: "Segoe UI Semibold", "Roboto", sans-serif;
                """);

        spinner = new ProgressIndicator();
        spinner.setPrefSize(60, 60);
        spinner.setStyle("-fx-progress-color: #10A37F;");

        root = new StackPane(spinner, output);
        root.setPadding(new Insets(18, 24, 18, 24));
        root.setAlignment(Pos.CENTER);
        root.setPrefWidth(PANEL_WIDTH);
        root.setMaxWidth(PANEL_WIDTH);
        root.setStyle("""
                    -fx-background-color: rgba(52,53,65,0.95);
                    -fx-background-radius: 10;
                    -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.45), 12, 0, 0, 3);
                """);
        root.setOpacity(0);

        root.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) close();
        });

        popup = new Popup();
        popup.getContent().add(root);
        popup.setAutoHide(false);
        popup.setHideOnEscape(false);

        autoClose = new Timeline(
                new KeyFrame(Duration.seconds(AUTO_CLOSE_SECONDS), ev -> close()));

        fadeIn = new FadeTransition(Duration.millis(150), root);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        fadeOut = new FadeTransition(Duration.millis(150), root);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setOnFinished(ev -> popup.hide());
    }

    public void showLoading() {
        Platform.runLater(() -> {
            spinner.setVisible(true);
            output.setVisible(false);
            open();
            autoClose.stop();
        });
    }

    public void show(String answer) {
        Platform.runLater(() -> {
            output.setText(answer.strip());
            spinner.setVisible(false);
            output.setVisible(true);
            open();
            autoClose.playFromStart();
        });
    }

    public void showError(String msg) {
        show("⚠ Erro:\n" + msg);
    }

    private void open() {
        if (!popup.isShowing()) {
            var v = Screen.getPrimary().getVisualBounds();
            double x = v.getMaxX() - PANEL_WIDTH - MARGIN;
            double y = v.getMaxY() - 200;
            popup.show(ownerWindow, x, y);

            root.applyCss();
            root.layout();

            double h = root.getHeight();

            popup.setX(x);
            popup.setY(v.getMaxY() - h - MARGIN);

            fadeIn.playFromStart();
        }
        root.requestFocus();
    }


    private void close() {
        fadeOut.playFromStart();
    }
}
