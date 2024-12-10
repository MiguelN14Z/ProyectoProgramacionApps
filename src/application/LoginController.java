package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usuario; // Campo para el nombre de usuario

    @FXML
    private PasswordField contrasena; // Campo para la contraseña 

    @FXML
    private Button boton1; // Botón de inicio de sesión

    /**
     * Maneja el evento de clic en el botón de inicio de sesión.
     * 
     * @param event el evento de acción generado por el botón.
     */
    @FXML
    public void login(ActionEvent event) {
        String username = usuario.getText(); // Obtener el texto ingresado en el campo de usuario
        String password = contrasena.getText(); // Obtener la contraseña ingresada

        // Validar que los campos no estén vacíos
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
                controller.setUsuario(username); // Pasa el nombre del usuario al controlador de Esce1

                // Cambiar la escena
                Stage stage = (Stage) boton1.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                // Manejar errores de carga del archivo FXML
                e.printStackTrace();
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error al cargar la escena");
                alert.setContentText("No se pudo cargar la siguiente escena. Revisa los archivos FXML.");
                alert.showAndWait();
            }
        }
    }
}
