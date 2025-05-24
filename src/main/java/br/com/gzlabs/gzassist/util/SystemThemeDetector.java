package br.com.gzlabs.gzassist.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public final class SystemThemeDetector {

    private static final Logger LOG = LoggerFactory.getLogger(SystemThemeDetector.class);

    private SystemThemeDetector() {
    }

    public static boolean isSystemDarkMode() {
        String osName = System.getProperty("os.name").toLowerCase();
        LOG.debug("Detecting system theme on OS: {}", osName);

        if (osName.contains("win")) {
            return isWindowsDarkMode();
        } else {
            LOG.warn("System theme detection is not implemented for OS: {}", osName);
            return false;
        }
    }

    private static boolean isWindowsDarkMode() {
        try {
            ProcessBuilder builder = new ProcessBuilder(
                    "reg", "query",
                    "HKEY_CURRENT_USER\\Software\\Microsoft\\Windows\\CurrentVersion\\Themes\\Personalize",
                    "/v", "AppsUseLightTheme"
            );

            Process process = builder.start();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    LOG.debug("Registry output: {}", line);
                    if (line.contains("AppsUseLightTheme")) {
                        String[] parts = line.trim().split("\\s+");
                        String value = parts[parts.length - 1];
                        boolean isDark = value.equals("0x0") || value.equals("0");
                        LOG.debug("Detected Windows theme: {}", isDark ? "DARK" : "LIGHT");
                        return isDark;
                    }
                }
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                LOG.warn("Registry query exited with non-zero code: {}", exitCode);
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOG.error("Thread interrupted while detecting Windows theme.", e);
        } catch (Exception e) {
            LOG.error("Failed to detect Windows theme.", e);
        }
        return false;
    }
}
