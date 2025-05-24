package br.com.gzlabs.gzassist.desktop;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TitleBarController {

    @FXML
    private Button minimizeButton;

    @FXML
    private Button closeButton;

    @FXML
    private HBox titleBar;

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    public void initialize() {
        titleBar.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        titleBar.setOnMouseDragged(event -> {
            Stage stage = (Stage) titleBar.getScene().getWindow();
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
    }

    @FXML
    protected void onMinimize() {
        Stage stage = (Stage) minimizeButton.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    protected void onClose() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
