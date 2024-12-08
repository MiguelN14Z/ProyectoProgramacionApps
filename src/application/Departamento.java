package application;

import java.sql.ResultSet;
import java.sql.SQLException;

import conexiondb.ConexionMySQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Departamento {
    private int codDepto;
    private String nombreDpto;

    // Constructor vacío (necesario para la llamada sin parámetros)
    public Departamento() {
    }

    // Constructor con parámetros
    public Departamento(int codDepto, String nombreDepto) {
        this.codDepto = codDepto;
        this.nombreDpto = nombreDpto;
    }

    // Getters y setters
    public int getCodDepto() {
        return codDepto;
    }

    public void setCodDepto(int codDepto) {
        this.codDepto = codDepto;
    }

    public String getNombreDepto() {
        return nombreDpto;
    }

    public void setNombreDepto(String nombreDpto) {
        this.nombreDpto = nombreDpto;
    }

    @Override
    public String toString() {
        return nombreDpto; // Esto es lo que se mostrará en el ChoiceBox
    }

    // Método para obtener los departamentos desde la base de datos
    public ObservableList<Departamento> getDepartamentos() {
        ObservableList<Departamento> obs = FXCollections.observableArrayList();
        try {
            ConexionMySQL conexion = new ConexionMySQL("localhost", "empleadoss_departamentoss", "root", "");
            conexion.ejecutarConsulta("SELECT * FROM departamentos");
            ResultSet rs = conexion.getResultSet();
            while (rs.next()) {
                int codDepto = rs.getInt("codDepto");
                String nombreDepto = rs.getString("nombreDpto");
                Departamento d = new Departamento(codDepto, nombreDepto);
                obs.add(d);
            }
            conexion.cerrarConexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obs;
    }
}
