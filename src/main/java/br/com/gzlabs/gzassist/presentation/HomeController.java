package br.com.gzlabs.gzassist.presentation;

import javafx.application.Platform;
import javafx.fxml.FXML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomeController {
    private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

    @FXML
    protected void onExamQuestionClick() {
        LOG.info("Mode: Capture & Solve Question selected");
    }

    @FXML
    protected void onCodeExplainClick() {
        LOG.info("Mode: Capture & Explain Code selected");
    }

    @FXML
    protected void onSummarizeClick() {
        LOG.info("Mode: Capture & Summarize Text selected");
    }

    @FXML
    protected void onTranslateClick() {
        LOG.info("Mode: Capture & Translate selected");
    }

    @FXML
    protected void onAutoDetectClick() {
        LOG.info("Mode: AutoDetect (AI decides) selected");
    }

    @FXML
    protected void onSettingsClick() {
        LOG.info("Settings button clicked");
    }

    @FXML
    protected void onExitClick() {
        LOG.info("Exit button clicked. Closing application.");
        Platform.exit();
    }
}
