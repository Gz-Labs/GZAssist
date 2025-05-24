package br.com.gzlabs.gzassist.desktop;

import br.com.gzlabs.gzassist.util.ThemeManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Objects;

public class TitleBarController {

    private static final Logger LOG = LoggerFactory.getLogger(TitleBarController.class);

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

    @FXML
    private Button settingsButton; // Adicione isso!

    @FXML
    protected void onSettingsClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/desktop/settings/settings-view.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root, 650, 450);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/desktop/settings/settings.css")).toExternalForm());
            ThemeManager.applyTheme(scene, root);

            Stage stage = new Stage();
            stage.setTitle("Settings");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(settingsButton.getScene().getWindow());
            stage.showAndWait();
        } catch (IOException e) {
            LOG.error("Failed to open the settings view.", e);
        }
    }
}
