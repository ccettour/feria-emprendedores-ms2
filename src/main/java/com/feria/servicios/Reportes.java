package com.feria.servicios;

import com.feria.modelos.*;

public class Reportes {

    public String generarReportePorCategoria(GestorFeria gestor, String categoria) {
        String reporte = "=== REPORTE DE EMPRENDEDORES - CATEGORÍA: " + categoria + " ===\n";

        for (Emprendedor e : gestor.emprendedores) {
            if (e.cat.equals(categoria)) {
                reporte += e.mostrarInfoYValidar();
                reporte += "---\n";
            }
        }

        return reporte;
    }

    public String generarReportePorCategoriaAlternativo(GestorFeria gestor, String cat) {
        String resultado = "REPORTE CATEGORIA " + cat + "\n";
        for (Emprendedor e : gestor.emprendedores) {
            if (e.cat.equals(cat)) {
                resultado += e.getNombre() + "\n";
            }
        }
        return resultado;
    }

    public double calcularVentasTotales(GestorFeria gestor) {
        double total = 0;
        for (Venta v : gestor.ventas) {
            total += v.calcularTotalConDescuento();
        }
        return total;
    }

    public void imprimirResumenEjecutivo(GestorFeria gestor) {
        System.out.println("========== RESUMEN EJECUTIVO ==========");
        System.out.println("Total emprendedores: " + gestor.emprendedores.size());
        System.out.println("Total productos: " + gestor.productos.size());
        System.out.println("Total ventas: " + gestor.ventas.size());

        double totalVentas = 0;
        for (Venta v : gestor.ventas) {
            totalVentas += v.calcularTotalConDescuento();
        }
        System.out.println("Total facturado: $" + totalVentas);

        int emprendedoresStockBajo = 0;
        for (Emprendedor e : gestor.emprendedores) {
            for (Producto p : e.prods) {
                if (p.stock < 5) {
                    emprendedoresStockBajo++;
                    break;
                }
            }
        }
        System.out.println("Emprendedores con stock bajo: " + emprendedoresStockBajo);
        System.out.println("=======================================");
    }
}