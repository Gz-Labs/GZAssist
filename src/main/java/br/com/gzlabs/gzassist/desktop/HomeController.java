package br.com.gzlabs.gzassist.desktop;

import br.com.gzlabs.gzassist.core.Mode;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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