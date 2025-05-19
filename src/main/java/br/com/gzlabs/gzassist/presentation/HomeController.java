package br.com.gzlabs.gzassist.presentation;

import br.com.gzlabs.gzassist.core.Mode;
import br.com.gzlabs.gzassist.util.ThemeManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Objects;

public class HomeController {

    private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);
    private static final String SELECTED_STYLE_CLASS = "selected";
    private Mode selectedMode = Mode.EXAM_QUESTION;

    @FXML
    private Button examQuestionBtn;
    @FXML
    private Button codeExplainBtn;
    @FXML
    private Button summarizeBtn;
    @FXML
    private Button translateBtn;
    @FXML
    private Button autoDetectBtn;

    private Button selectedBtn;

    @FXML
    protected void onExamQuestionClick() {
        highlightSelected(examQuestionBtn);
        selectedMode = Mode.EXAM_QUESTION;
        LOG.info("Mode: Capture & Solve Question selected");
    }

    @FXML
    protected void onCodeExplainClick() {
        highlightSelected(codeExplainBtn);
        selectedMode = Mode.CODE_EXPLAIN;
        LOG.info("Mode: Capture & Explain Code selected");
    }

    @FXML
    protected void onSummarizeClick() {
        highlightSelected(summarizeBtn);
        selectedMode = Mode.SUMMARIZE;
        LOG.info("Mode: Capture & Summarize Text selected");
    }

    @FXML
    protected void onTranslateClick() {
        highlightSelected(translateBtn);
        selectedMode = Mode.TRANSLATE;
        LOG.info("Mode: Capture & Translate selected");
    }

    @FXML
    protected void onAutoDetectClick() {
        highlightSelected(autoDetectBtn);
        selectedMode = Mode.AUTO_DETECT;
        LOG.info("Mode: AutoDetect (AI decides) selected");
    }

    @FXML
    protected void onSettingsClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("settings-view.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 650, 450);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("settings.css")).toExternalForm());
        ThemeManager.applyDark(scene, root);

        Stage stage = new Stage();
        stage.setTitle("Settings");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(examQuestionBtn.getScene().getWindow());
        stage.showAndWait();
    }


    @FXML
    protected void onExitClick() {
        LOG.info("Exit button clicked. Closing application.");
        Platform.exit();
    }

    private void highlightSelected(Button btn) {
        if (selectedBtn != null && selectedBtn != btn) {
            selectedBtn.getStyleClass().remove(SELECTED_STYLE_CLASS);
        }
        if (!btn.getStyleClass().contains(SELECTED_STYLE_CLASS)) {
            btn.getStyleClass().add(SELECTED_STYLE_CLASS);
        }
        selectedBtn = btn;
    }

    public Mode getSelectedMode() {
        return selectedMode;
    }

}
