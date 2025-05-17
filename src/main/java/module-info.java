module br.com.gzlabs.gzassist {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.slf4j;
    requires jintellitype;
    requires openai.java.core;
    requires openai.java.client.okhttp;

    exports br.com.gzlabs.gzassist;
    exports br.com.gzlabs.gzassist.app;
    exports br.com.gzlabs.gzassist.ui;
    exports br.com.gzlabs.gzassist.capture;
    exports br.com.gzlabs.gzassist.ai;

    opens br.com.gzlabs.gzassist to javafx.fxml;
    opens br.com.gzlabs.gzassist.app to javafx.fxml;
    opens br.com.gzlabs.gzassist.ui to javafx.fxml;
}