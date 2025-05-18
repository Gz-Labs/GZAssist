package br.com.gzlabs.gzassist.presentation;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.StackPane;
import javafx.stage.Popup;
import javafx.stage.Screen;
import javafx.stage.Window;
import javafx.util.Duration;

public final class OverlayController {

    @FXML private StackPane rootPane;
    @FXML private ProgressIndicator spinner;
    @FXML private Label messageLabel;

    private Popup popup;
    private Window ownerWindow;

    private static final int AUTO_CLOSE_SECONDS = 5;
    private static final double PANEL_WIDTH = 450;
    private static final double MARGIN = 20;
    private static final String ERROR_STYLE_CLASS = "error";

    private FadeTransition fadeIn;
    private FadeTransition fadeOut;
    private Timeline autoClose;

    public void init(Window ownerWindow) {
        this.ownerWindow = ownerWindow;
        this.popup = new Popup();
        popup.getContent().add(rootPane);

        this.fadeIn = createFadeTransition(0, 1);
        this.fadeOut = createFadeTransition(1, 0);
        fadeOut.setOnFinished(e -> popup.hide());

        this.autoClose = new Timeline(
                new KeyFrame(Duration.seconds(AUTO_CLOSE_SECONDS), e -> close())
        );
    }

    public void handleUiEvent(UiEvent event) {
        Platform.runLater(() -> {
            clearPreviousState();

            switch (event.type()) {
                case LOADING -> {
                    spinner.setVisible(true);
                    show();
                    autoClose.stop();
                }
                case SUCCESS -> {
                    showMessage(event.message(), false);
                    autoClose.playFromStart();
                }
                case ERROR -> {
                    showMessage("⚠ Erro:\n" + event.message(), true);
                    autoClose.playFromStart();
                }
            }
        });
    }

    private void clearPreviousState() {
        spinner.setVisible(false);
        messageLabel.setVisible(false);
        messageLabel.setText("");
    }

    private void showMessage(String message, boolean isError) {
        messageLabel.setText(message);
        messageLabel.getStyleClass().remove(ERROR_STYLE_CLASS);
        if (isError) {
            messageLabel.getStyleClass().add(ERROR_STYLE_CLASS);
        }

        spinner.setVisible(false);
        messageLabel.setVisible(true);

        var listener = new javafx.beans.value.ChangeListener<Number>() {
            @Override
            public void changed(javafx.beans.value.ObservableValue<? extends Number> obs, Number oldVal, Number newVal) {
                messageLabel.heightProperty().removeListener(this);
                show();
            }
        };

        messageLabel.heightProperty().addListener(listener);

        new javafx.animation.PauseTransition(Duration.millis(100))
                .setOnFinished(e -> show());
    }


    private void show() {
        if (!popup.isShowing()) {
            popup.show(ownerWindow);
        }

        Platform.runLater(() -> {
            rootPane.applyCss();
            rootPane.layout();

            var screen = Screen.getPrimary().getVisualBounds();
            double x = screen.getMaxX() - PANEL_WIDTH - MARGIN;
            double y = screen.getMaxY() - rootPane.getHeight() - MARGIN;

            popup.setX(x);
            popup.setY(y);

            fadeIn.playFromStart();
        });
    }

    private void close() {
        fadeOut.playFromStart();
    }

    private FadeTransition createFadeTransition(double from, double to) {
        FadeTransition ft = new FadeTransition(Duration.millis(150), rootPane);
        ft.setFromValue(from);
        ft.setToValue(to);
        return ft;
    }
}
