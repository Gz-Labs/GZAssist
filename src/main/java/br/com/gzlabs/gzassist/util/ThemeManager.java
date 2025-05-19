package br.com.gzlabs.gzassist.util;

import javafx.scene.Parent;
import javafx.scene.Scene;

import java.util.Objects;

public class ThemeManager {

    private ThemeManager() {
    }

    public static void applyDark(Scene scene, Parent root) {
        apply(scene, root, "theme-dark.css", "theme-dark");
    }

    public static void applyLight(Scene scene, Parent root) {
        apply(scene, root, "theme-light.css", "theme-light");
    }

    private static void apply(Scene scene, Parent root, String themeFile, String themeClass) {
        scene.getStylesheets().clear();
        scene.getStylesheets().addAll(
                Objects.requireNonNull(ThemeManager.class.getResource("/br/com/gzlabs/gzassist/presentation/style.css")).toExternalForm(),
                Objects.requireNonNull(ThemeManager.class.getResource("/br/com/gzlabs/gzassist/presentation/" + themeFile)).toExternalForm()
        );
        root.getStyleClass().removeAll("theme-dark", "theme-light");
        root.getStyleClass().add(themeClass);
    }
}
