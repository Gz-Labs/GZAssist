module br.com.gabrizord.gzedusolve {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.slf4j;
    requires jintellitype;
    requires java.desktop;
    requires openai.java.core;
    requires openai.java.client.okhttp;


    opens br.com.gabrizord.gzedusolve to javafx.fxml;
    exports br.com.gabrizord.gzedusolve;
}