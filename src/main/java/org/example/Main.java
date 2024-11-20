package org.example;

import org.example.bbdditems.Proxecto;

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

        // visualizarEmpleadosDeLocalidad("Lugo");

        // cambiarDepartamentoDeProyecto("PERSOAL","PORTAL");

         //  Proxecto proyectonuevo = new Proxecto(11,"BallAndPinpong","America",1);
        // añadirProyecto(proyectonuevo);

           // eliminarProyecto(11);

        // cambioDomicilio("1010001", "Awaewa",69,"77", 23241, "awaweria");

        // getDatosProyectos(10);

        // getDepartamentosConMasDeXPryectos(2);
        
        // getRsTypesConc();

        //insertProyecto(proyectonuevo);

        // System.out.println(existeProyecto("BallAndPinpong", 11));
        // System.out.println(existeDepartamento( 7));

        //subirSueldoEmpleadosDepartamento(1, 10000);

        obtenerDatosDeEmpleadosConXproyectos(1);


    }
}