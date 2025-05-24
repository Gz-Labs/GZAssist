module br.com.gzlabs.gzassist {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.slf4j;
    requires jintellitype;
    requires openai.java.core;
    requires openai.java.client.okhttp;
    requires java.prefs;

    exports br.com.gzlabs.gzassist.core;
    exports br.com.gzlabs.gzassist.desktop;
    exports br.com.gzlabs.gzassist.application;
    exports br.com.gzlabs.gzassist.errors;
    exports br.com.gzlabs.gzassist;

    opens br.com.gzlabs.gzassist.desktop to javafx.fxml;
}
