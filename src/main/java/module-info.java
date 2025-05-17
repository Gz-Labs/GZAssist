module br.com.gzlabs.gzassist {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.slf4j;
    requires jintellitype;
    requires java.desktop;
    requires openai.java.core;
    requires openai.java.client.okhttp;


    opens br.com.gzlabs.gzassist to javafx.fxml;
    opens br.com.gzlabs.gzassist.ui to javafx.fxml;
    exports br.com.gzlabs.gzassist;
    exports br.com.gzlabs.gzassist.ui;

}