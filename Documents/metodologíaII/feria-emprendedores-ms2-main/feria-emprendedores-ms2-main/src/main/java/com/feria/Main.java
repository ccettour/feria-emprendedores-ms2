package com.feria;

import com.feria.modelos.*;
import com.feria.servicios.*;
import com.feria.utils.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        GestorFeria gestor = new GestorFeria();
        Reportes reportes = new Reportes();

        Emprendedor ana = EmprendedorFactory.crearEmprendedor(
            "Ana", "E001", "3423456789", "ana@gmail.com", "comida",
            Arrays.asList("Empanadas", "Tortas", "Alfajores"),
            Arrays.asList(500.0, 1500.0, 300.0),
            Arrays.asList(50, 10, 100)
        );
        gestor.emprendedores.add(ana);
        gestor.productos.addAll(ana.productos);

        Emprendedor carlos = EmprendedorFactory.crearEmprendedor(
            "Carlos", "E002", "3423987654", "carlos@hotmail.com", "artesania",
            Arrays.asList("Collar", "Pulsera"),
            Arrays.asList(2000.0, 800.0),
            Arrays.asList(5, 20)
        );
        gestor.emprendedores.add(carlos);
        gestor.productos.addAll(carlos.productos);

        gestor.registrarVenta("V001", "E001", "Empanadas", 10, 500.0, "2026-05-12");
        gestor.registrarVenta("V002", "E002", "Collar", 1, 2000.0, "2026-05-12");

        System.out.println(reportes.generarReportePorCategoria(gestor, "comida"));

        gestor.procesarVentasPendientesYCobrar();

        reportes.imprimirResumenEjecutivo(gestor);

        System.out.println("Emprendedor Ana válido? " + Validadores.validarEmprendedorCompleto(gestor.emprendedores.get(0)));

        System.out.println(gestor.emprendedores.get(0).mostrarInfo());
    }
}