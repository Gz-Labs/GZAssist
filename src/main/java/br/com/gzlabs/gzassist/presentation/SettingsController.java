package br.com.gzlabs.gzassist.presentation;

import br.com.gzlabs.gzassist.config.AppConfig;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SettingsController {

    @FXML
    private TextField apiKeyField;
    @FXML
    private TextField hotkeyField;
    @FXML
    private Spinner<Integer> timeoutSpinner;

    @FXML
    public void initialize() {
        apiKeyField.setText(AppConfig.getApiKey());
        hotkeyField.setText(AppConfig.getHotkey());
        timeoutSpinner.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 60, AppConfig.getOverlayTimeout())
        );
    }

    @FXML
    protected void onSaveClick() {
        AppConfig.setApiKey(apiKeyField.getText().trim());
        AppConfig.setHotkey(hotkeyField.getText().trim());
        AppConfig.setOverlayTimeout(timeoutSpinner.getValue());
        closeWindow();
    }

    @FXML
    protected void onCancelClick() {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) apiKeyField.getScene().getWindow();
        stage.close();
    }
}
