package org.example.ejercicio2;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

import static org.example.ejercicio2.Ejercicio2.conexion;

public class Ejercicio3 {

    /*

    Exercicio 3.1. Obtención de información sobre o SXBD e a conexión

    Realiza un programa Java para establecer unha conexión co SXBD MySQL, que acceda á base de datos BDEmpresa, implemente e chame o seguinte método. Controla os posibles erros e separa a chamada aos métodos da implementación deles en clases diferentes. – Crea un método para que mostre a seguinte información: nome do SXBD, número de versión do SXBD, número de versión principal do SXBD, número de versión secundario do SXBD, nome do conectador JDBC utilizado, número da versión principal do conectador JDBC, número da versión secundaria do conectador JDBC, número de versión do conectador JDBC utilizado, URL da base de datos, nome do usuario actual conectado á base de datos e si a base de datos é de só lectura.

     */

    public static void getMetadata3_1() throws SQLException {

        DatabaseMetaData metadata = conexion.getMetaData();

        System.out.println("Nombre de la BBDD: " + metadata.getDatabaseProductName());
        System.out.println("Driver mayor de la BBDD: " + metadata.getDriverMajorVersion());
        System.out.println("Driver menor de la BBDD: " + metadata.getDriverMinorVersion());
        System.out.println("JDBC menor de la BBDD: " + metadata.getJDBCMinorVersion());
        System.out.println("JDBC mayor de la BBDD: " + metadata.getJDBCMajorVersion());
        System.out.println("URL de la BBDD: " + metadata.getURL());
        System.out.println("Nombre de Usuario conectado: " + metadata.getUserName());
        System.out.println("Lectura solo: " + metadata.isReadOnly());
        System.out.println("\n");

    }

 /*
        Exercicio 3.2. Acceso aos metadatos referente as táboas e os procedementos

        No programa anterior da tarefa 1, engade os seguintes métodos:

        a) Método que mostre información de todas as táboas de usuario da base de datos BDEmpresa.

        b) Método que reciba un esquema e unha táboa como parámetros e visualice as súas columnas. Mostrarase para cada columna, o nome, tipo de datos, tamaño e si admite valores nulos ou non.

        c) Método que mostre información de todos os procedementos da base de datos BDEmpresa.

        d) Método que reciba un esquema e unha táboa como parámetros e visualice as súas columnas de clave primaria.

        e) Método que reciba un esquema e unha táboa e visualice as súas columnas de clave foráneas. Mostrarase para cada columna de clave foránea a columna e a táboa a que referencia.


  */


    public static void getMetadata3_2_ab(String nombretabla) throws SQLException {

        DatabaseMetaData metadata = conexion.getMetaData();

        try (ResultSet columnas = metadata.getColumns(null, "empresa", nombretabla, null);) {
            while (columnas.next()) {
                String nombre = columnas.getString("COLUMN_NAME");
                String tipo = columnas.getString("TYPE_NAME");
                String tamaño = columnas.getString("COLUMN_SIZE");
                String nula = columnas.getString("IS_NULLABLE");
                System.out.println("Columna: " + nombre + ", Tipo: " + tipo +
                        "Tamaño: " + tamaño + ", ¿Puede ser nula? " + nula);
            }
            System.out.println("\n");
        }
    }

    public static void getMetadata3_2_c() throws SQLException {

        DatabaseMetaData metadata = conexion.getMetaData();

        try (ResultSet rs = metadata.getProcedures(null, null, "%")) {

            while (rs.next()) {

                String procedureName = rs.getString("PROCEDURE_NAME");
                String schema = rs.getString("PROCEDURE_SCHEM");
                System.out.println("Procedimiento: " + procedureName);
                System.out.println("Esquema: " + schema);
                System.out.println("---");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getMetadata3_2_d(String nombretabla, String nombrebasedatos) throws SQLException {

        DatabaseMetaData metadata = conexion.getMetaData();

        try (ResultSet columnas = metadata.getPrimaryKeys(nombrebasedatos, nombrebasedatos, nombretabla);) {
            while (columnas.next()) {

                String tipo = columnas.getString("PK_NAME");
                String nombre = columnas.getString("COLUMN_NAME");
                System.out.println("Columna Primaria: " + nombre + ", Tipo: " + tipo);

            }
            System.out.println("\n");
        }
    }

    public static void getMetadata3_2_e(String nombretabla, String nombrebasedatos) throws SQLException {

        DatabaseMetaData metadata = conexion.getMetaData();

        try (ResultSet columnas = metadata.getExportedKeys(nombrebasedatos, nombrebasedatos, nombretabla);) {
            while (columnas.next()) {

                String tipo = columnas.getString("FK_NAME");
                String nombre = columnas.getString("FKCOLUMN_NAME");
                String referenciaFK = columnas.getString("PKTABLE_NAME");
                String referenciaPK = columnas.getString("PKCOLUMN_NAME");
                String key = columnas.getString("KEY_SEQ");

                System.out.println("Nombre de la clave foránea (FK_NAME): " + tipo);
                System.out.println("Columna en la tabla que es la clave foránea (FKCOLUMN_NAME): " + nombre);
                System.out.println("Tabla referenciada por la clave foránea (PKTABLE_NAME): " + referenciaFK);
                System.out.println("Columna referenciada en la tabla (PKCOLUMN_NAME): " + referenciaPK);
                System.out.println("Posición de la clave foránea (KEY_SEQ): " + key);

            }
            System.out.println("\n");
        }
    }
    /*



    Exercicio 3.3. Acceso aos metadatos referente as funcións, procedementos e características dispoñibles do SXBD

    Engade ao programa creado na tarefa1, un método para que mostre a seguinte información: as funcións de cadea, data e hora, matemáticas e de sistema dispoñibles polo SXBD, a lista de palabras reservadas, a cadea utilizada para delimitar os identificadores, a cadea de escape de carácteres comodíns, se o usuario conectado pode chamar a todos os procedementos e acceder a todas a táboas.


     */


    public static void getMetadata3_3() throws SQLException {

        DatabaseMetaData metadata = conexion.getMetaData();

        System.out.println("Funciones de fecha y hora: " + metadata.getTimeDateFunctions());
        System.out.println("\nFunciones de cadena: " + metadata.getStringFunctions());
        System.out.println("\nFunciones de sistema: " + metadata.getSystemFunctions());
        System.out.println("\nFunciones matemáticas: " + metadata.getNumericFunctions());
        System.out.println("\nPalabras reservadas de SQL: " + metadata.getSQLKeywords());
        System.out.println("\nCadena de escape de comodines: " + metadata.getSearchStringEscape());
        System.out.println("\n¿El usuario puede llamar a todos los procedimientos?: " + metadata.allProceduresAreCallable());
        System.out.println("\n¿El usuario puede acceder a todas las tablas?: " + metadata.allTablesAreSelectable());

    }

    /*

    Exercicio 3.4. Acceso aos metadatos dos límites impostos polo conectador

    Engade ao programa creado na tarefa1, un método para que mostre a seguinte información: o número de conexións simultáneas, o número máximo de sentenzas simultáneas, número máximo de táboas nunha consulta SELECT, a lonxitude máxima do nome dunha táboa, dunha columna, dunha sentenza SQL, dunha fila ou dun nome de procedemento, e o número máximo de columnas nunha cláusula ORDER, SELECT ou GROUP BY.

     */

    public static void getMetadata3_4() throws SQLException {

        DatabaseMetaData metadata = conexion.getMetaData();

        System.out.println("Número de conexiones simultáneas: " + metadata.getMaxConnections());
        System.out.println("\nNúmero máximo de sentencias simultáneas: " + metadata.getMaxStatements());
        System.out.println("\nNúmero máximo de tablas en una consulta SELECT: " + metadata.getMaxTablesInSelect());
        System.out.println("\nLongitud máxima del nombre de una tabla: " + metadata.getMaxTableNameLength());
        System.out.println("\nLongitud máxima del nombre de una columna: " + metadata.getMaxColumnNameLength());
        System.out.println("\nLongitud máxima de una sentencia SQL: " + metadata.getMaxStatementLength());
        System.out.println("\nLongitud máxima de una fila: " + metadata.getMaxRowSize());
        System.out.println("\nLongitud máxima del nombre de un procedimiento: " + metadata.getMaxProcedureNameLength());
        System.out.println("\nNúmero máximo de columnas en una cláusula ORDER, SELECT o GROUP BY: " + metadata.getMaxColumnsInGroupBy());

    }

    /*

    Exercicio 3.5. Metadatos sobre as transaccións

    Engade ao programa creado na tarefa1, un método para que mostre a seguinte información: si o SXBD soporta as transaccións, o nivel de illamento de transaccións predeterminado e si se soportan sentenzas de manipulación de datos e de definición de datos dentro das transaccións.

     */


    public static void getMetadata3_5() throws SQLException {

        DatabaseMetaData metadata = conexion.getMetaData();

        System.out.println("¿Soporta transacciones?: " + metadata.supportsTransactions());
        System.out.println("\nNive illamento de transaccións predeterminado: " + metadata.getDefaultTransactionIsolation());
        System.out.println("\n¿Soporta sentencias de manipulado de datos en transacciones?: " + metadata.supportsDataManipulationTransactionsOnly());
        System.out.println("\n¿Soporta sentencias de definición de datos en transacciones?: " + metadata.supportsDataDefinitionAndDataManipulationTransactions());


    }

    /*

    Exercicio 3.6. Métodos sobre o soporte de características

    Engade ao programa creado na tarefa1, un método para que mostre a seguinte información: si na orde ALTER TABLE se pode utilizar ADD COLUMN e DROP COLUMN, si nos alias de columnas pode empregarse a palabra AS, si o resultado de concatenar un NULL e un NOT NULL resulta NULL, si se soportan as conversións entre tipos de datos JDBC, si se soportan os nomes de táboas correlacionadas, si se soporta o uso dunha columna que non está na instrución SELECT nunha cláusula ORDER BY, si se soporta a cláusula GROUP BY, si se admite o uso dunha columna que non está na instrución SELECT nunha cláusula GROUP BY, si se soportan as cláusulas LIKE, si se soportan os outer joins, si se soportan subconsultas EXISTS e si se soportan subconsultas nas expresións de comparación, nas expresións IN e nas nas expresións cuantificadas.

     */

    public static void getMetadata3_6() throws SQLException {

        DatabaseMetaData metadata = conexion.getMetaData();

        System.out.println("¿Se puede utilizar ADD COLUMN y DROP COLUMN en ALTER TABLE?: " + metadata.supportsAlterTableWithAddColumn());
        System.out.println("\n¿Se puede utilizar la palabra AS en los alias de columnas?: " + metadata.supportsColumnAliasing());
        System.out.println("\n¿El resultado de concatenar un NULL y un NOT NULL resulta NULL?: " + metadata.nullPlusNonNullIsNull());
        System.out.println("\n¿Se soportan las conversiones entre tipos de datos JDBC?: " + metadata.supportsConvert());
        System.out.println("\n¿Se soportan los nombres de tablas correlacionadas?: " + metadata.supportsCorrelatedSubqueries());
        System.out.println("\n¿Se soporta el uso de una columna que no está en la instrucción SELECT en una cláusula ORDER BY?: " + metadata.supportsExpressionsInOrderBy());
        System.out.println("\n¿Se soporta la cláusula GROUP BY?: " + metadata.supportsGroupBy());
        System.out.println("\n¿Se admite el uso de una columna que no está en la instrucción SELECT en una cláusula GROUP BY?: " + metadata.supportsGroupByUnrelated());
        System.out.println("\n¿Se soportan las cláusulas LIKE?: " + metadata.supportsLikeEscapeClause());
        System.out.println("\n¿Se soportan los outer joins?: " + metadata.supportsOuterJoins());
        System.out.println("\n¿Se soportan subconsultas EXISTS?: " + metadata.supportsSubqueriesInExists());
        System.out.println("\n¿Se soportan subconsultas en las expresiones de comparación, en las expresiones IN y en las expresiones cuantificadas?: " + metadata.supportsSubqueriesInComparisons());

    }

    /*
    Exercicio 3.7. Metadatos do ResultSet

    Engade ao programa creado na tarefa1, un método que reciba unha consulta (por ex. SELECT * FROM proxecto) e imprima o número de columnas recuperadas, e por cada columna: o nome, tipo, tamaño e si admite ou non nulos.

     */


    public static void getMetadata3_7() throws SQLException {

        String query = "SELECT * FROM proxecto";

        Statement statement = conexion.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        ResultSetMetaData metadata = resultSet.getMetaData();

        int columnas = metadata.getColumnCount();
        System.out.println("Número de columnas: " + columnas);

        for (int i = 1; i <= columnas; i++) {
            System.out.println("Nombre de la columna: " + metadata.getColumnName(i));
            System.out.println("Tipo de la columna: " + metadata.getColumnTypeName(i));
            System.out.println("Tamaño de la columna: " + metadata.getColumnDisplaySize(i));
            System.out.println("¿Puede ser nula?: " + metadata.isNullable(i));
        }



    }
}
