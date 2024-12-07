package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginController {
    @FXML
    private TextField usuario;
    @FXML
    private TextField contrasena;
    @FXML
    private Button boton1;

    @FXML
    public void login(ActionEvent event) {
        String username = usuario.getText();
        String password = contrasena.getText();

        if (username.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText("Campos vacíos");
            alert.setContentText("Por favor, ingresa un usuario y contraseña.");
            alert.showAndWait();
        } else {
            try {
                // Cargar Esce1.fxml
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Esce1.fxml"));
                Pane root = loader.load();

                // Obtener el controlador de Esce1 y pasar el nombre del usuario
                Esce1Controller controller = loader.getController();
                controller.setUsuario(username);  // Pasa el nombre del usuario al TextField de Esce1.fxml

                // Cambiar la escena
                Stage stage = (Stage) boton1.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
