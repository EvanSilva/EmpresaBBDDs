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

    2.3 Realiza un programa Java para establecer unha conexión co SXBD MySQL, acceda á base de datos BDEmpresa, implemente e chame os seguintes métodos. Utiliza sentencias pre-compiladas (preparadas) e controla os posibles erros. Separa a chamada aos métodos da implementación deles en clases diferentes.

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

    /*

        Exercicio 2.5. Execución de procedementos almacenados e funcións.

        Realiza un programa Java para establecer unha conexión co SXBD MySql, acceda á base de datos BDEmpresa, implemente e chame os seguintes métodos. Controla os posibles erros e separa a chamada aos métodos da implementación deles en clases diferentes.

        a)

        – Primeiro, na base de datos BDEmpresa, crea un procedemento almacenado chamado pr_cambioDomicilio para que modifique a dirección dun empregado cos datos que se lle pasan por parámetro. O procedemento recibirá como parámetros o nss do empre-gado, e os novos datos: rúa, número, piso, código postal e localidade.

        – Crea un método que chame ao procedemento sp_cambioDomicilio. O método recibirá como parámetros o nss do empregado, a rúa, o número, o piso, o código postal e a localidade.

        b)

        – Crea un procedemento almacenado chamado pr_DatosProxectos que reciba un número de proxecto e devolva o nome, lugar e número de departamento de dito proxecto. O procedemento terá un parámetro de entrada e tres de saída.

        – Crea un método que chame ao procedemento pr_DatosProxectos. O método recibirá como parámetros o número de proxecto e devolverá un obxecto proxecto. c)

        – Crea un procedemento almacenado chamado pr_DepartControlaProxec que mostre os datos dos departamentos que controlan un número de proxectos igual ou maior que un valor enteiro pasado por parámetro.

        – Crea un método que chame ao procedemento pr_DepartControlaProxec. O método recibirá como parámetros un número enteiro, e visualizará os datos dos departamentos que controlan un número de proxectos igual ou maior que o valor pasado por parámetro. Utiliza a instrución execute para a chamada ao procedemento. Visualiza tamén si se tratou dunha sentenza de actualización ou de selección.

        d)

        – Crea unha función chamada fn_nEmpDepart que dado o nome do departamento, devolva o número de empregados de dito departamento.

        – Crea un método que dado o nome do departamento, execute a anterior función visualizando o resultado.

        [NO SÉ HACERLO]


     */

    public static void cambioDomicilio(String nss, String rua, int numero, String piso, int cp, String localidade ) {

        String procedimiento = "{ CALL pr_cambioDomicilio(?, ?, ?, ?, ?, ?) }";

        try (CallableStatement callableStatement = conexion.prepareCall(procedimiento)) {

            callableStatement.setString(1, nss);
            callableStatement.setString(2, rua);
            callableStatement.setInt(3, numero);
            callableStatement.setString(4, piso);
            callableStatement.setInt(5, cp);
            callableStatement.setString(6, localidade);
            callableStatement.execute();

            System.out.println("Dirección del empleado actualizada correctamente.");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Error: Integridad de datos violada. Verifica los datos ingresados.");
        } catch (SQLException e) {
            System.out.println("Error al ejecutar el procedimiento: " + e.getMessage());
        }
    }

    public static void getDatosProyectos(int numProyecto) {
        String statement = "{ CALL pr_DatosProxectos(?, ?, ?, ?) }";

        try (CallableStatement callableStatement = conexion.prepareCall(statement)) {
            // Configurar el parámetro de entrada
            callableStatement.setInt(1, numProyecto);

            // Configurar los parámetros de salida
            callableStatement.registerOutParameter(2, Types.VARCHAR);
            callableStatement.registerOutParameter(3, Types.VARCHAR);
            callableStatement.registerOutParameter(4, Types.INTEGER);

            // Ejecutar el procedimiento
            callableStatement.execute();

            // Obtener los resultados de los parámetros de salida
            String nome = callableStatement.getString(2);
            String lugar = callableStatement.getString(3);
            int numDepartamento = callableStatement.getInt(4);

            // Imprimir los resultados
            System.out.println("Nome do proxecto: " + nome);
            System.out.println("Lugar do proxecto: " + lugar);
            System.out.println("Número de departamento: " + numDepartamento);

        } catch (SQLException e) {
            e.printStackTrace(); // Mostrar información del error
            System.err.println("Erro ao executar o procedemento: " + e.getMessage());
        }
    }

    public static void getDepartamentosConMasDeXPryectos(int numProyectos) {
        String statement = "{ CALL pr_DepartControlaProxec(?) }";

        try (CallableStatement callableStatement = conexion.prepareCall(statement)) {
            // Configurar el parámetro de entrada
            callableStatement.setInt(1, numProyectos);

            // Ejecutar el procedimiento
            boolean hasResults = callableStatement.execute();

            // Si el procedimiento devuelve un ResultSet
            if (hasResults) {
                ResultSet resultSet = callableStatement.getResultSet();

                // Recorrer el ResultSet y mostrar todos los registros
                while (resultSet.next()) {
                    int numDepartamento = resultSet.getInt("Num_departamento");
                    String nomeDepartamento = resultSet.getString("Nome_departamento");
                    int numProxectos = resultSet.getInt("NumProxectos");

                    // Imprimir los resultados
                    System.out.println("Numero do Departamento: " + numDepartamento);
                    System.out.println("Nome Departamento: " + nomeDepartamento);
                    System.out.println("Número de Proxectos: " + numProxectos);
                    System.out.println("----------------------------");
                }
            } else {
                System.out.println("Non hai departamentos que controlen tantos proxectos.");
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Mostrar información del error
            System.err.println("Erro ao executar o procedemento: " + e.getMessage());
        }
    }

    /*

    Exercicio 2.6. Xestión do resultado dunha consulta.

    Realiza un programa Java para establecer unha conexión co SXBD MySql, que acceda á base de datos BDEmpresa, implemente e chame os seguintes métodos. Controla os posibles erros e separa a chamada aos métodos da implementación deles en clases diferentes.

    a) Crea un método para visualizar os tipos de ResultSet e a concorrencia soportada polo conectador JDBC de MySQL.

     */

    public static void getRsTypesConc(){
        try{
            DatabaseMetaData dbmd = conexion.getMetaData();
            if (dbmd.supportsResultSetConcurrency(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE))
                System.out.println("Soporta TYPE_SCROLL_SENSITIVE y CONCUR_UPDATABLE");
            if (dbmd.supportsResultSetConcurrency(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE))
                System.out.println("Soporta TYPE_SCROLL_INSENSITIVE y CONCUR_UPDATABLE");
            if (dbmd.supportsResultSetConcurrency(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE))
                System.out.println("Soporta TYPE_FORWARD_ONLY y CONCUR_UPDATABLE");
            if (dbmd.supportsResultSetConcurrency(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY))
                System.out.println("Soporta TYPE_SCROLL_SENSITIVE y CONCUR_READ_ONLY");
            if (dbmd.supportsResultSetConcurrency(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY))
                System.out.println("Soporta TYPE_SCROLL_INSENSITIVE y CONCUR_READ_ONLY");
            if (dbmd.supportsResultSetConcurrency(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY))
                System.out.println("Soporta TYPE_FORWARD_ONLY y CONCUR_READ_ONLY");

        }catch (SQLException e){
            System.out.println("Error al contar los empleados de departamento.");
            e.printStackTrace();
        }
    }



}


