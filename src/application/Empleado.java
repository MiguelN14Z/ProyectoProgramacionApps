package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class Empleado {
    private int id;
    private String nombre;
    private String sexo;
    private String fechaNacimiento;
    private String fechaIncorporacion;
    private double salario;
    private double comision;
    private String cargo;
    private int jefeID;
    private int codDepto;

    // Constructor
    public Empleado(int id, String nombre, String sexo, String fechaNacimiento, String fechaIncorporacion,
                    double salario, double comision, String cargo, int jefeID, int codDepto) {
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
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getSexo() { return sexo; }
    public String getFechaNacimiento() { return fechaNacimiento; }
    public String getFechaIncorporacion() { return fechaIncorporacion; }
    public double getSalario() { return salario; }
    public double getComision() { return comision; }
    public String getCargo() { return cargo; }
    public int getJefeID() { return jefeID; }
    public int getCodDepto() { return codDepto; }

    // Método para obtener empleados desde la base de datos
    public ObservableList<Empleado> getEmpleados() {
        ObservableList<Empleado> empleados = FXCollections.observableArrayList();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/empleadoss_departamentoss", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM empleados");
            while (rs.next()) {
                int id = rs.getInt("nDIEmp");
                String nombre = rs.getString("nomEmp");
                String sexo = rs.getString("sexEmp");
                String fechaNacimiento = rs.getString("fecNac");
                String fechaIncorporacion = rs.getString("fecIncorporacion");
                double salario = rs.getDouble("salEmp");
                double comision = rs.getDouble("comisionE");
                String cargo = rs.getString("cargoE");
                int jefeID = rs.getInt("jefeID");
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

    // Método para insertar un nuevo empleado en la base de datos
    public boolean insertarEmpleado(String dni, String nombre, String sexo, String fechaNacimiento,
                                    String fechaIncorporacion, double salario, double comision, String cargo,
                                    int jefeID, int codDepto) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/empleadoss_departamentoss", "root", "");
            String query = "INSERT INTO empleados (nDIEmp, nomEmp, sexEmp, fecNac, fecIncorporacion, salEmp, comisionE, cargoE, jefeID, codDepto) "
                         + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, dni);
            pst.setString(2, nombre);
            pst.setString(3, sexo);
            pst.setString(4, fechaNacimiento);
            pst.setString(5, fechaIncorporacion);
            pst.setDouble(6, salario);
            pst.setDouble(7, comision);
            pst.setString(8, cargo);
            pst.setInt(9, jefeID);
            pst.setInt(10, codDepto);
            int rowsAffected = pst.executeUpdate();
            conn.close();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
