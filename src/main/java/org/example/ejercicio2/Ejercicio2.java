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

    // Exercicio 2.1: Actualización de datos utilizando a interface Statement

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

    // c) Fai un método para borrar un empregado dun proxecto. O método recibirá como parámetros o nss do empregado e o número do proxecto.

    public static void eliminarEmpleadoDeProyecto(int numeroProyecto, String nss) throws SQLException {

        String sqUpdate = "DELETE FROM empregado_proxecto WHERE NSS_Empregado =  '" + nss + "' AND Num_proxecto = " + numeroProyecto;

        try (Statement statementUpdate = conexion.createStatement()) {
            int filasActualizadas = statementUpdate.executeUpdate(sqUpdate);
            System.out.println("Filas actualizadas: " + filasActualizadas);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    // Exercicio 2.2. Consulta de datos utilizando a interface Statement.

    // No programa anterior, engade un método para visualizar os nomes, apelidos, localidade, salario, data de nacemento, nome do empregado xefe e o nome do departamento onde traballan, de aqueles empregados dunha determinada localidade. O método recibirá por parámetro o nome da localidade. Para executar as sentenzas utilizarase a interface Statement e deberanse controlar os posibles erros.

    public static void visualizarEmpleadosDeLocalidad(String localidade) throws SQLException {

        String sqUpdate = "SELECT * FROM empregado WHERE Localidade = '" + localidade + "'";

        String NombreDepartamento = "SELECT * FROM empregado WHERE Localidade = '" + localidade + "'";

        try {

            Statement statementFetch = conexion.createStatement();

            ResultSet respuesta = statementFetch.executeQuery(sqUpdate);

            System.out.println("Los datos de los empleados de " + localidade + " son:");

            while (respuesta.next()) {

                String nome = respuesta.getString("Nome");
                String apel1 = respuesta.getString("Apelido_1");
                String apel2 = respuesta.getString("Apelido_2");
                String localidad = respuesta.getString("Localidade");
                Integer salario = respuesta.getInt("Salario");
                String nacemento = respuesta.getString("Data_nacemento");

                String nombrJefe = "SELECT e2.Nome AS NombreJefe " +
                        "FROM empregado e1 " +
                        "JOIN empregado e2 ON e1.Nss_supervisa = e2.Nss " +
                        "WHERE e1.Nss = '" + respuesta.getString("NSS") + "'";

                String nombrDepartamento = "SELECT departamento.nombre_departamento AS nombrDepartamento " +
                        "FROM empregado " +
                        "INNER JOIN departamento ON empregado.departamento_id = departamento.departamento_id";



                ResultSet respuestaJefe = statementFetch.executeQuery(nombrJefe);

                ResultSet respuestaDepartamento = statementFetch.executeQuery(nombrDepartamento);

                System.out.println("Los datos del empleado " + nome + " son: \n" +
                        nome + "\n" +
                        apel1 + "\n" +
                        apel2 + "\n" +
                        localidad + "\n" +
                        salario + "\n" +
                        nacemento + "\n" +
                        salario + "\n" +
                        respuestaJefe.getString("NombreJefe") + "\n" +
                        respuestaDepartamento.getString("nombrDepartamento") + "\n"
                );

            }

            respuesta.close();
            statementFetch.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

}

