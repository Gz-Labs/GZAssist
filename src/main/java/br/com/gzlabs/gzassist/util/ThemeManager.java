package br.com.gzlabs.gzassist.util;

import javafx.scene.Parent;
import javafx.scene.Scene;

import java.util.Objects;

public class ThemeManager {

    private ThemeManager() {
    }

    public static void applyDark(Scene scene, Parent root) {
        scene.getStylesheets().addAll(
                Objects.requireNonNull(ThemeManager.class.getResource("/br/com/gzlabs/gzassist/presentation/style.css")).toExternalForm(),
                Objects.requireNonNull(ThemeManager.class.getResource("/br/com/gzlabs/gzassist/presentation/theme-dark.css")).toExternalForm()
        );
        root.getStyleClass().add("theme-dark");
    }
}

