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
    public String getId() { return id; }  // Cambiado a String
    public String getNombre() { return nombre; }
    public String getSexo() { return sexo; }
    public String getFechaNacimiento() { return fechaNacimiento; }
    public String getFechaIncorporacion() { return fechaIncorporacion; }
    public double getSalario() { return salario; }
    public double getComision() { return comision; }
    public String getCargo() { return cargo; }
    public String getJefeID() { return jefeID; }  // Cambiado a String
    public int getCodDepto() { return codDepto; }

    // Método para obtener empleados desde la base de datos
    public ObservableList<Empleado> getEmpleados() {
        ObservableList<Empleado> empleados = FXCollections.observableArrayList();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/empleadoss_departamentoss", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM empleados");
            while (rs.next()) {
                String id = rs.getString("nDIEmp");  // Usar getString
                String nombre = rs.getString("nomEmp");
                String sexo = rs.getString("sexEmp");
                String fechaNacimiento = rs.getString("fecNac");
                String fechaIncorporacion = rs.getString("fecIncorporacion");
                double salario = rs.getDouble("salEmp");
                double comision = rs.getDouble("comisionE");
                String cargo = rs.getString("cargoE");
                String jefeID = rs.getString("jefeID");  // Usar getString
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

	public boolean insertarEmpleado(String dniValue, String nombreValue, String generoValue, String nacimientoValue,
			String incorporacionValue, double double1, double double2, String cargoValue, String id2, int codDepto2) {
		// TODO Auto-generated method stub
		return false;
	}
}
