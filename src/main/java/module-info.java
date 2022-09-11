module cifrado.vignere.cifradovignere {
    requires javafx.controls;
    requires javafx.fxml;

    requires validatorfx;

    opens cifrado.vignere.cifradovignere to javafx.fxml;
    exports cifrado.vignere.cifradovignere;
}