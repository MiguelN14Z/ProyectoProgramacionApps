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
        // Obtener los valores de los campos de texto
        String username = usuario.getText();
        String password = contrasena.getText();

        // Validar que los campos no estén vacíos
        if (username.isEmpty() || password.isEmpty()) {
            // Mostrar un mensaje de advertencia
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText("Campos vacíos");
            alert.setContentText("Por favor, ingresa un usuario y contraseña.");
            alert.showAndWait();
        } else {
            try {
                // Cargar la nueva escena desde Esce1.fxml
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Esce1.fxml"));
                Pane root = loader.load();

                // Obtener el Stage actual desde el botón
                Stage stage = (Stage) boton1.getScene().getWindow();

                // Configurar la nueva escena y mostrarla
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace(); // Manejar errores de carga del FXML
            }
        }
    }
}
