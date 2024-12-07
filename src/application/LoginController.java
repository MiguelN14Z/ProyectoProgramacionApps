package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private Button button1;
    @FXML
    private TextField usuario;
    @FXML
    private TextField contrasena;

    @FXML
    private void ingresar() {
        String username = usuario.getText();
        String password = contrasena.getText();


        if (username != null && password != null && !username.isEmpty() && !password.isEmpty()) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Esce1.fxml"));
            Esce1Controller controller = new Esce1Controller();

            try {
                loader.setController(controller);
                loader.load();
                Stage stage = (Stage) button1.getScene().getWindow();
                Scene scene = new Scene(loader.getRoot(), 400, 300);
                stage.setScene(scene);
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}