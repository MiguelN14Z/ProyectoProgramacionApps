package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;
import javafx.collections.ObservableList;

public class Esce2Controller {

    @FXML
    private TextField dni;
    @FXML
    private TextField nombre;
    @FXML
    private TextField salario;
    @FXML
    private TextField comision;
    @FXML
    private TextField cargo;
    @FXML
    private ComboBox<Empleado> jefe; 
    @FXML
    private ComboBox<Departamento> departamento; 
    @FXML
    private DatePicker nacimiento;
    @FXML
    private DatePicker incorporacion;
    @FXML
    private ComboBox<String> genero;

    @FXML
    private Button botonGuardar;
    @FXML
    private Button botonRegresar;

    public void initialize() {
        genero.getItems().addAll("Masculino", "Femenino", "Otro");

        // Cargar los valores de jefe desde la base de datos
        Empleado empleado = new Empleado(null, null, null, null, null, 0, 0, null, null, 0);
        ObservableList<Empleado> jefes = empleado.getEmpleados();
        jefe.setItems(jefes);

        // Personalizar el ComboBox de jefe para mostrar solo el código (ID)
        jefe.setCellFactory(lv -> new ListCell<Empleado>() {
            @Override
            protected void updateItem(Empleado item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getId());
            }
        });
        jefe.setButtonCell(new ListCell<Empleado>() {
            @Override
            protected void updateItem(Empleado item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getId());
            }
        });

        // Cargar los valores de departamento desde la base de datos
        Departamento depto = new Departamento();
        ObservableList<Departamento> departamentos = depto.getDepartamentos();
        departamento.setItems(departamentos);

        // Personalizar el ComboBox de departamento
        departamento.setCellFactory(lv -> new ListCell<Departamento>() {
            @Override
            protected void updateItem(Departamento item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getCodDepto() + " - " + item.getNombreDpto());
            }
        });
        departamento.setButtonCell(new ListCell<Departamento>() {
            @Override
            protected void updateItem(Departamento item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getCodDepto() + " - " + item.getNombreDpto());
            }
        });
    }

    @FXML
    public void onGuardarEmpleado(ActionEvent event) {
        String dniValue = dni.getText();
        String nombreValue = nombre.getText();
        String cargoValue = cargo.getText();
        String salarioValue = salario.getText();
        String comisionValue = comision.getText();
        String generoValue = genero.getValue();
        Empleado jefeSeleccionado = jefe.getValue();
        Departamento departamentoSeleccionado = departamento.getValue();
        String nacimientoValue = nacimiento.getValue() != null ? nacimiento.getValue().toString() : null;
        String incorporacionValue = incorporacion.getValue() != null ? incorporacion.getValue().toString() : null;

        // Convertir género a abreviaturas
        String generoAbreviado = null;
        if (generoValue != null) {
            switch (generoValue) {
                case "Masculino":
                    generoAbreviado = "M";
                    break;
                case "Femenino":
                    generoAbreviado = "F";
                    break;
                case "Otro":
                    generoAbreviado = "O";
                    break;
            }
        }

        // Validar campos
        String errores = "";
        if (dniValue.isEmpty()) errores += "- DNI es obligatorio\n";
        if (nombreValue.isEmpty()) errores += "- Nombre es obligatorio\n";
        if (cargoValue.isEmpty()) errores += "- Cargo es obligatorio\n";
        if (salarioValue.isEmpty()) errores += "- Salario es obligatorio\n";
        if (comisionValue.isEmpty()) errores += "- Comisión es obligatoria\n";
        if (generoAbreviado == null) errores += "- Selecciona un género válido\n";
        if (jefeSeleccionado == null) errores += "- Selecciona un jefe\n";
        if (departamentoSeleccionado == null) errores += "- Selecciona un departamento\n";

        if (errores.isEmpty()) {
            // Guardar empleado en la base de datos
            Empleado empleado = new Empleado(dniValue, nombreValue, generoAbreviado, nacimientoValue, incorporacionValue,
                    Double.parseDouble(salarioValue), Double.parseDouble(comisionValue),
                    cargoValue, jefeSeleccionado.getId(), departamentoSeleccionado.getCodDepto());

            if (empleado.insertarEmpleado(dniValue, nombreValue, generoAbreviado, nacimientoValue, incorporacionValue,
                    Double.parseDouble(salarioValue), Double.parseDouble(comisionValue),
                    cargoValue, jefeSeleccionado.getId(), departamentoSeleccionado.getCodDepto())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Éxito");
                alert.setContentText("Empleado guardado exitosamente");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("No se pudo guardar el empleado");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errores de validación");
            alert.setContentText(errores);
            alert.showAndWait();
        }
    }

    @FXML
    private void onRegresar(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Esce1.fxml"));
        try {
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            Stage currentStage = (Stage) botonRegresar.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

