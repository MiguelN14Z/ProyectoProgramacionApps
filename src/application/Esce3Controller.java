package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class Esce3Controller {

    @FXML
    private TableView<Empleado> tablaEmpleados;
    @FXML
    private TableColumn<Empleado, String> colId;
    @FXML
    private TableColumn<Empleado, String> colNombre;
    @FXML
    private TableColumn<Empleado, String> colSexo;
    @FXML
    private TableColumn<Empleado, String> colFechaNacimiento;
    @FXML
    private TableColumn<Empleado, String> colFechaIncorporacion;
    @FXML
    private TableColumn<Empleado, Double> colSalario;
    @FXML
    private TableColumn<Empleado, Double> colComision;
    @FXML
    private TableColumn<Empleado, String> colCargo;
    @FXML
    private TableColumn<Empleado, String> colJefe;
    @FXML
    private TableColumn<Empleado, Integer> colCodDepto;

    @FXML
    private ChoiceBox<String> busqueda; // ChoiceBox para nombres de empleados

    @FXML
    private Button regresar; // Botón para regresar a la escena anterior

    private ObservableList<Empleado> empleados;

    @FXML
    public void initialize() {
        // Configurar columnas
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        colFechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        colFechaIncorporacion.setCellValueFactory(new PropertyValueFactory<>("fechaIncorporacion"));
        colSalario.setCellValueFactory(new PropertyValueFactory<>("salario"));
        colComision.setCellValueFactory(new PropertyValueFactory<>("comision"));
        colCargo.setCellValueFactory(new PropertyValueFactory<>("cargo"));
        colJefe.setCellValueFactory(new PropertyValueFactory<>("jefeID"));
        colCodDepto.setCellValueFactory(new PropertyValueFactory<>("codDepto"));

        // Cargar datos
        cargarDatos();
        configurarBusqueda();
    }

    private void cargarDatos() {
        Empleado empleado = new Empleado(null, null, null, null, null, 0, 0, null, null, 0);
        empleados = empleado.getEmpleados();
        tablaEmpleados.setItems(empleados);
    }

    private void configurarBusqueda() {
        // Cargar nombres de empleados en el ChoiceBox
        ObservableList<String> nombres = FXCollections.observableArrayList();
        for (Empleado empleado : empleados) {
            nombres.add(empleado.getNombre());
        }
        busqueda.setItems(nombres);

        // Agregar un listener al ChoiceBox para resaltar en la tabla
        busqueda.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                for (Empleado empleado : empleados) {
                    if (empleado.getNombre().equals(newValue)) {
                        tablaEmpleados.getSelectionModel().select(empleado); // Resalta el empleado en la tabla
                        tablaEmpleados.scrollTo(empleado); // Asegura que sea visible en la tabla
                        break;
                    }
                }
            }
        });
    }

    @FXML
    private void handleRegresar(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Esce1.fxml"));
        try {
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            Stage currentStage = (Stage) regresar.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
