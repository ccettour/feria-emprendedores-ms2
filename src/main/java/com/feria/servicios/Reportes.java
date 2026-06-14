package com.feria.servicios;

import com.feria.modelos.*;

public class Reportes {

    public String generarReportePorCategoria(GestorFeria gestor, String categoria) {
        String reporte = "=== REPORTE DE EMPRENDEDORES - CATEGORÍA: " + categoria + " ===\n";
        for (Emprendedor emprendedor : gestor.emprendedores) {
            if (emprendedor.categoria.equals(categoria)) {
                reporte += emprendedor.mostrarInfo();
                reporte += "---\n";
            }
        }
        return reporte;
    }

    public String generarReportePorCategoriaResumido(GestorFeria gestor, String categoria) {
        String resultado = "REPORTE CATEGORÍA " + categoria + "\n";
        for (Emprendedor emprendedor : gestor.emprendedores) {
            if (emprendedor.categoria.equals(categoria)) {
                resultado += emprendedor.getNombre() + "\n";
            }
        }
        return resultado;
    }

    public double calcularVentasTotales(GestorFeria gestor) {
        double total = 0;
        for (Venta venta : gestor.ventas) {
            total += venta.calcularTotalConDescuento();
        }
        return total;
    }

    public void imprimirResumenEjecutivo(GestorFeria gestor) {
        System.out.println("========== RESUMEN EJECUTIVO ==========");
        System.out.println("Total emprendedores: " + gestor.emprendedores.size());
        System.out.println("Total productos: " + gestor.productos.size());
        System.out.println("Total ventas: " + gestor.ventas.size());
        System.out.println("Total facturado: $" + calcularVentasTotales(gestor));
        System.out.println("Emprendedores con stock bajo: " + contarEmprendedoresConStockBajo(gestor));
        System.out.println("=======================================");
    }

    private int contarEmprendedoresConStockBajo(GestorFeria gestor) {
        int contador = 0;
        for (Emprendedor emprendedor : gestor.emprendedores) {
            for (Producto producto : emprendedor.productos) {
                if (producto.stock < 5) {
                    contador++;
                    break;
                }
            }
        }
        return contador;
    }
}