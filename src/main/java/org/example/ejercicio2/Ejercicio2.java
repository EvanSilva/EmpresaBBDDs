package org.example.ejercicio2;

import org.example.bbdditems.Proxecto;

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

        String sqUpdate = """
                SELECT\s
                    e.Nome,
                    e.Apelido_1,
                    e.Apelido_2,
                    e.Localidade,
                    e.Salario,
                    e.Data_nacemento,
                    (SELECT nome\s
                     FROM EMPREGADO\s
                     WHERE NSS = e.NSS_Supervisa) AS Jefe,
                    (SELECT Nome_departamento\s
                		FROM DEPARTAMENTO
                        WHERE Num_departamento = E.Num_departamento_pertenece) AS Departamento
                FROM\s
                    EMPREGADO e
                WHERE\s
                    e.Localidade = '"""+  localidade + "'; "

        ;

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
                String nss = respuesta.getString("Jefe");
                String departamento = respuesta.getString("Departamento");


                System.out.println("Los datos del empleado " + nome + " son: \n" +
                        "Nombre: "+  nome + "\n" +
                        "Apellido1: "+  apel1 + "\n" +
                        "Apellido2: "+  apel2 + "\n" +
                        "Localidad: "+ localidad  + "\n" +
                        "Salario: "+ salario  + "\n" +
                        "Nacimiento: "+  nacemento + "\n" +
                        "Jefe: "+  nss + "\n" +
                        "Departamento: "+  departamento + "\n"
                );

            }

            respuesta.close();
            statementFetch.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /*

    Realiza un programa Java para establecer unha conexión co SXBD MySQL, acceda á base de datos BDEmpresa, implemente e chame os seguintes métodos. Utiliza sentencias pre-compiladas (preparadas) e controla os posibles erros. Separa a chamada aos métodos da implementación deles en clases diferentes.

    a) Fai un método para cambiar o departamento que controla un proxecto. O método recibirá como parámetros o nome do departamento e o nome do proxecto.

    b) Fai un método para inserir un novo proxecto. O método recibirá como parámetro un obxecto proxecto. Crea a clase proxecto, cos métodos setter e getter, e coa mesma estrutura que a táboa proxecto.

    c) Fai un método para borrar un proxecto. O método recibirá como parámetros o número do proxecto. Tamén debes borrar a información da asignación dos empregados ao proxecto.


     */

    public static void cambiarDepartamentoDeProyecto(String nombreDepartamento, String nombreProyecto) throws SQLException {

        String sqUpdate = """
                UPDATE proxecto AS p
                	SET p.Num_departamento = (
                							SELECT d.Num_departamento
                								FROM departamento AS d
                								WHERE d.Nome_departamento = '""" + nombreDepartamento + """
                	')
                	WHERE p.Num_proxecto = (
                		SELECT Num_proxecto
                		FROM (SELECT Num_proxecto FROM proxecto WHERE Nome_proxecto = '""" + nombreProyecto +  """
                ') AS temp
                );

                """;

        try (Statement statementUpdate = conexion.createStatement()) {
            int filasActualizadas = statementUpdate.executeUpdate(sqUpdate);
            System.out.println("Filas actualizadas: " + filasActualizadas);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }



    public static void añadirProyecto (Proxecto proyecto) throws SQLException {

        String sqUpdate = """ 
                INSERT INTO proxecto(Num_proxecto,Nome_proxecto,Lugar,Num_departamento) 
                VALUES("""+ proyecto.getNum_proxecto() + ",'"+ proyecto.getNome_proxecto()+"','"+ proyecto.getLugar()+"',"+proyecto.getNum_departamento()+");";


        try (Statement statementUpdate = conexion.createStatement()) {
            // Ejecutar actualización
            int filasActualizadas = statementUpdate.executeUpdate(sqUpdate);
            System.out.println("Filas actualizadas: " + filasActualizadas);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

    }

    public static void eliminarProyecto (int idProyecto) throws SQLException {

        String sqUpdate = """
                
                DELETE FROM proxecto
                        WHERE Num_proxecto = """ + idProyecto + """
                ;
                """;


        try (Statement statementUpdate = conexion.createStatement()) {
            // Ejecutar actualización
            int filasActualizadas = statementUpdate.executeUpdate(sqUpdate);
            System.out.println("Filas actualizadas: " + filasActualizadas);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

    }




}

