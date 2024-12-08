package application;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
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
    private ComboBox<Empleado> jefe; // Cambiado a Empleado para mostrar objetos reales
    @FXML
    private ComboBox<Departamento> departamento; // Cambiado a Departamento para mostrar objetos reales
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

    // Método para inicializar los valores
    public void initialize() {
        // Inicializa el ComboBox de género
        genero.getItems().addAll("Masculino", "Femenino", "Otro");

        // Cargar los valores de jefe desde la base de datos
        Empleado empleado = new Empleado(0, null, null, null, null, 0, 0, null, 0, 0);
        ObservableList<Empleado> jefes = empleado.getEmpleados();
        jefe.setItems(jefes);

        // Cargar los valores de departamento desde la base de datos
        Departamento depto = new Departamento();
        ObservableList<Departamento> departamentos = depto.getDepartamentos();
        departamento.setItems(departamentos);
    }

    // Método para manejar la selección de un jefe
    @FXML
    public void jefe(ActionEvent event) {
        Empleado jefeSeleccionado = jefe.getValue();
        if (jefeSeleccionado != null) {
            System.out.println("Jefe seleccionado: " + jefeSeleccionado.getNombre());
        }
    }

    // Método para manejar la selección de un departamento
    @FXML
    public void depto(ActionEvent event) {
        Departamento departamentoSeleccionado = departamento.getValue();
        if (departamentoSeleccionado != null) {
            System.out.println("Departamento seleccionado: " + departamentoSeleccionado.getNombreDepto());
        }
    }

    // Método para guardar el empleado
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

        // Validar campos
        String errores = "";
        if (dniValue.isEmpty()) errores += "- DNI es obligatorio\n";
        if (nombreValue.isEmpty()) errores += "- Nombre es obligatorio\n";
        if (cargoValue.isEmpty()) errores += "- Cargo es obligatorio\n";
        if (salarioValue.isEmpty()) errores += "- Salario es obligatorio\n";
        if (comisionValue.isEmpty()) errores += "- Comisión es obligatoria\n";
        if (generoValue == null) errores += "- Selecciona un género\n";
        if (jefeSeleccionado == null) errores += "- Selecciona un jefe\n";
        if (departamentoSeleccionado == null) errores += "- Selecciona un departamento\n";

        if (errores.isEmpty()) {
            // Guardar empleado en la base de datos
            Empleado empleado = new Empleado(0, nombreValue, generoValue, nacimientoValue, incorporacionValue,
                    Double.parseDouble(salarioValue), Double.parseDouble(comisionValue),
                    cargoValue, jefeSeleccionado.getId(), departamentoSeleccionado.getCodDepto());

            if (empleado.insertarEmpleado(dniValue, nombreValue, generoValue, nacimientoValue, incorporacionValue,
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

    // Método para regresar
    @FXML
    public void onRegresar(ActionEvent event) {
        Stage stage = (Stage) botonRegresar.getScene().getWindow();
        stage.close();
    }
}
