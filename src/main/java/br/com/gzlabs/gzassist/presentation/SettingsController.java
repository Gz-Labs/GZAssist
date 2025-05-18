package br.com.gzlabs.gzassist.presentation;

import br.com.gzlabs.gzassist.config.AppConfig;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
        carregarConfiguracoesGerais();
    }

    private void carregarConfiguracoesGerais() {
        apiKeyField.setText(AppConfig.getApiKey());
        hotkeyField.setText(AppConfig.getHotkey());
        timeoutSpinner.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 60, AppConfig.getOverlayTimeout())
        );
    }

    @FXML
    protected void onSaveClick() {
        salvarConfiguracoesGerais();
        closeWindow();
    }

    private void salvarConfiguracoesGerais() {
        AppConfig.setApiKey(apiKeyField.getText().trim());
        AppConfig.setHotkey(hotkeyField.getText().trim());
        AppConfig.setOverlayTimeout(timeoutSpinner.getValue());
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Settings saved successfully!", ButtonType.OK);
        alert.setHeaderText(null);
        alert.showAndWait();
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
