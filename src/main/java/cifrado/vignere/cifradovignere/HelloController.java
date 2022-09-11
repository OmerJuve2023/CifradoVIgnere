package cifrado.vignere.cifradovignere;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import metodos.metodosVignere;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    TextArea result;
    @FXML
    TextField semilla;
    @FXML
    TextField texto;
    @FXML
    Button start;

    private final metodosVignere vig = new metodosVignere();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vig.onlyLetters(semilla);
        vig.onlyLetters(texto);

        start.visibleProperty().bind(semilla.textProperty().isEmpty().not().and(texto.textProperty().isEmpty().not()));
        vig.generateMatriz();
    }

    public void setResult() {
        if (semilla.getText().length() > texto.getText().length()) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setHeaderText("se recortara la semilla al ser muy grande");
            a.show();
        }
        vig.generateEncription(result, semilla.getText().toLowerCase().replace(" ", "").trim(),
                texto.getText().toLowerCase().replace(" ", "").trim());
    }
}