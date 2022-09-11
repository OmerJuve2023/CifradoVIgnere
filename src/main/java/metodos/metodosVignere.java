package metodos;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import java.nio.charset.StandardCharsets;
import java.util.function.UnaryOperator;

public class metodosVignere {

    private static final int[][] matriz = new int[26][26];

    public void generateMatriz() {
        int a = 96;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                a++;
                if (a > 122) a = 97;
                matriz[i][j] = a;
            }
            a = 96 + i + 1;
        }
    }

    public void generateEncription(TextArea textArea, String semilla, String texto) {

        byte[] textos = texto.getBytes(StandardCharsets.UTF_8);
        byte[] semillas = Descompose(semilla, texto);

        StringBuilder txt = new StringBuilder();
        for (int k = 0; k < texto.length(); k++) {
            int a = 0, b = 0;
            for (int i = 0; i < 26; i++) {
                if (textos[k] == matriz[i][0]) {
                    a = i;
                }
            }
            for (int j = 0; j < 26; j++) {
                if (semillas[k] == matriz[0][j]) {
                    b = j;
                }
            }
            txt.append((char) matriz[a][b]);
            textArea.setText(txt.toString());

        }

    }

    private byte[] Descompose(String semilla, String texto) {

        for (int i = 0; i < texto.length(); i++) {
            if (semilla.length() < texto.length()) semilla += semilla;
            else break;
        }
        semilla = semilla.substring(0, texto.length());
        return semilla.getBytes(StandardCharsets.UTF_8);
    }

    public void onlyLetters(TextField textField) {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();
            if (!text.matches("[0-9]")) return change;
            return null;
        };
        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        textField.setTextFormatter(textFormatter);
    }

}
