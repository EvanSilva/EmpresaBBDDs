package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.example.ejercicio2.Ejercicio2.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) throws SQLException {

        connectDB();

        // sumarSalarioDepartamento(200, "CONTABILIDADE");

        // añadirNuevoDepartamento(9, "RecursosHermanos", "0010010");

        // eliminarEmpleadoDeProyecto(8,"0010010");

        visualizarEmpleadosDeLocalidad("Vigo");





    }
}