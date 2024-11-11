package org.example.ejercicio2;

import java.sql.*;
public class Ejercicio2 {

    public static Connection conexion;

    public static void connectDB() {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "root");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // a) Fai un método para subir o salario aos empregados dun determinado departamento. O método recibirá como parámetros a cantidade a aumentar e o nome do departamento.

    public static void sumarSalarioDepartamento (int cantidad, String nombreDepartamento) throws SQLException {

        String sqUpdate = "UPDATE empregado SET salario = salario + " + cantidad + " WHERE Num_departamento_pertenece = (SELECT Num_departamento FROM departamento WHERE Nome_departamento = '" + nombreDepartamento + "' )";

        try (Statement statementUpdate = conexion.createStatement()) {
            // Ejecutar actualización
            int filasActualizadas = statementUpdate.executeUpdate(sqUpdate);
            System.out.println("Filas actualizadas: " + filasActualizadas);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

    }

    // b) Fai un método para inserir un novo departamento. O método recibirá como parámetros o número, nome do departamento e o nss do empregado director. A data do comezo do director do departamento será a data do sistema.

    public static void añadirNuevoDepartamento(int numeroNuevoDepartamento, String nombreDepartamento, String nss) throws SQLException {

        String sqUpdate = "INSERT INTO departamento VALUES (" + numeroNuevoDepartamento + ", '" + nombreDepartamento + "', '" + nss + "', CURRENT_DATE)";

        try (Statement statementUpdate = conexion.createStatement()) {
            int filasActualizadas = statementUpdate.executeUpdate(sqUpdate);
            System.out.println("Filas actualizadas: " + filasActualizadas);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }



}
