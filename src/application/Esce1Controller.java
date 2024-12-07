package application;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Esce1Controller {
    @FXML
    private Button button1;
    @FXML
    private TextField usuario;  // El TextField donde se mostrará el nombre
    @FXML
    private ComboBox<String> comboBox;

    @FXML
    public void initialize() {
        // Configura las opciones del ComboBox
        comboBox.setItems(FXCollections.observableArrayList("Ingresar Empleado", "Consultar Empleados"));
    }

    // Método para establecer el nombre del usuario en el TextField
    @FXML
    public void setUsuario(String nombreUsuario) {
        usuario.setText(nombreUsuario);  // Muestra el nombre del usuario en el TextField
    }



    @FXML
    public void ingresar(ActionEvent event) {
        System.out.println("Botón 'Ingresar' presionado.");
        System.out.println("Usuario: " + usuario.getText());
    }

    @FXML
    public void select(ActionEvent event) {
        String selectedOption = comboBox.getValue();
        System.out.println("Opción seleccionada: " + selectedOption);

        if ("Ingresar Empleado".equals(selectedOption)) {
            cargarEscena("Esce2.fxml");
        } else if ("Consultar Empleados".equals(selectedOption)) {
            cargarEscena("Esce3.fxml");
        }
    }

    private void cargarEscena(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Stage stage = (Stage) button1.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
