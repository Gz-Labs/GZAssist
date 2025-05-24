package br.com.gzlabs.gzassist.util;

import javafx.scene.Parent;
import javafx.scene.Scene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public final class ThemeManager {

    private static final Logger LOG = LoggerFactory.getLogger(ThemeManager.class);

    private ThemeManager() {
    }

    public static void applyTheme(Scene scene, Parent root) {
        boolean isDark = SystemThemeDetector.isSystemDarkMode();
        String themeFile = isDark ? "theme-dark.css" : "theme-light.css";
        String themeClass = isDark ? "theme-dark" : "theme-light";

        LOG.debug("Applying {} theme.", isDark ? "dark" : "light");

        scene.getStylesheets().clear();
        scene.getStylesheets().addAll(
                Objects.requireNonNull(ThemeManager.class.getResource("/desktop/common/main-style.css")).toExternalForm(),
                Objects.requireNonNull(ThemeManager.class.getResource("/desktop/theme/" + themeFile)).toExternalForm()
        );

        root.getStyleClass().removeAll("theme-dark", "theme-light");
        root.getStyleClass().add(themeClass);
    }
}
