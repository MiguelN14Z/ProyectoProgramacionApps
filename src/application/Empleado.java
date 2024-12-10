package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class Empleado {
    private String id;  // Cambiado a String
    private String nombre;
    private String sexo;
    private String fechaNacimiento;
    private String fechaIncorporacion;
    private double salario;
    private double comision;
    private String cargo;
    private String jefeID;  // Cambiado a String
    private int codDepto;

    // Constructor
    public Empleado(String id, String nombre, String sexo, String fechaNacimiento, String fechaIncorporacion,
                    double salario, double comision, String cargo, String jefeID, int codDepto) {
        this.id = id;
        this.nombre = nombre;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaIncorporacion = fechaIncorporacion;
        this.salario = salario;
        this.comision = comision;
        this.cargo = cargo;
        this.jefeID = jefeID;
        this.codDepto = codDepto;
    }

    // Métodos getter y setter
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getSexo() { return sexo; }
    public String getFechaNacimiento() { return fechaNacimiento; }
    public String getFechaIncorporacion() { return fechaIncorporacion; }
    public double getSalario() { return salario; }
    public double getComision() { return comision; }
    public String getCargo() { return cargo; }
    public String getJefeID() { return jefeID; }
    public int getCodDepto() { return codDepto; }

    // Método para obtener empleados desde la base de datos
    public ObservableList<Empleado> getEmpleados() {
        ObservableList<Empleado> empleados = FXCollections.observableArrayList();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/empleadoss_departamentoss", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM empleados");
            while (rs.next()) {
                String id = rs.getString("nDIEmp");
                String nombre = rs.getString("nomEmp");
                String sexo = rs.getString("sexEmp");
                String fechaNacimiento = rs.getString("fecNac");
                String fechaIncorporacion = rs.getString("fecIncorporacion");
                double salario = rs.getDouble("salEmp");
                double comision = rs.getDouble("comisionE");
                String cargo = rs.getString("cargoE");
                String jefeID = rs.getString("jefeID");
                int codDepto = rs.getInt("codDepto");

                Empleado empleado = new Empleado(id, nombre, sexo, fechaNacimiento, fechaIncorporacion, salario, comision, cargo, jefeID, codDepto);
                empleados.add(empleado);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empleados;
    }

    // Método para insertar un empleado en la base de datos
    public boolean insertarEmpleado(String dniValue, String nombreValue, String generoValue, String nacimientoValue,
                                    String incorporacionValue, double salario, double comision, String cargoValue,
                                    String jefeID, int codDepto) {
        String query = "INSERT INTO empleados (nDIEmp, nomEmp, sexEmp, fecNac, fecIncorporacion, salEmp, comisionE, cargoE, jefeID, codDepto) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/empleadoss_departamentoss", "root", "");
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, dniValue);
            stmt.setString(2, nombreValue);
            stmt.setString(3, generoValue);
            stmt.setString(4, nacimientoValue);
            stmt.setString(5, incorporacionValue);
            stmt.setDouble(6, salario);
            stmt.setDouble(7, comision);
            stmt.setString(8, cargoValue);
            stmt.setString(9, jefeID);
            stmt.setInt(10, codDepto);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
