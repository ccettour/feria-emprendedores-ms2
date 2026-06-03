package com.feria.servicios;

import com.feria.modelos.*;
import com.feria.utils.Validadores;
import java.util.ArrayList;
import java.util.List;

public class GestorFeria {

    public List<Emprendedor> emprendedores;
    public List<Producto> productos;
    public List<Venta> ventas;

    public GestorFeria() {
        emprendedores = new ArrayList<>();
        productos = new ArrayList<>();
        ventas = new ArrayList<>();
    }

    public void registrarEmprendedorConProductos(String nombre, String id, String telefono,
                                                   String email, String categoria,
                                                   List<String> nombresProductos,
                                                   List<Double> precios,
                                                   List<Integer> stocks) {
        if (nombre == null || nombre.length() < 2) {
            System.out.println("Error: nombre inválido");
            return;
        }
        if (!Validadores.emailValido(email)) {
            System.out.println("Error: email inválido");
            return;
        }

        Emprendedor emprendedor = new Emprendedor(nombre, id, telefono, email, categoria);

        for (int i = 0; i < nombresProductos.size(); i++) {
            Producto producto = new Producto(nombresProductos.get(i), precios.get(i), stocks.get(i), categoria, id);
            emprendedor.agregarProducto(producto);
            productos.add(producto);
        }

        emprendedores.add(emprendedor);
        System.out.println("Emprendedor registrado con " + nombresProductos.size() + " productos");
    }

    public void registrarVenta(String idVenta, String empId, String nombreProducto, int cantidad, double precio, String fecha) {
        Producto productoEncontrado = buscarProducto(nombreProducto, empId);

        if (productoEncontrado == null) {
            System.out.println("Producto no encontrado");
            return;
        }
        if (productoEncontrado.stock < cantidad) {
            System.out.println("Stock insuficiente");
            return;
        }

        Venta venta = new Venta(idVenta, empId, nombreProducto, cantidad, precio, fecha);
        ventas.add(venta);
        productoEncontrado.stock -= cantidad;
        System.out.println("Venta registrada. Nuevo stock: " + productoEncontrado.stock);
    }

    private Producto buscarProducto(String nombreProducto, String empId) {
        for (Producto p : productos) {
            if (p.nombre.equals(nombreProducto) && p.emprendedorId.equals(empId)) {
                return p;
            }
        }
        return null;
    }

    public List<Emprendedor> getEmprendedoresConStockBajo() {
        List<Emprendedor> resultado = new ArrayList<>();
        for (Emprendedor emprendedor : emprendedores) {
            for (Producto producto : emprendedor.productos) {
                if (producto.hayStockBajo()) {
                    resultado.add(emprendedor);
                    break;
                }
            }
        }
        return resultado;
    }

    public void procesarVentasPendientesYCobrar() {
        double totalRecaudado = 0;
        for (Venta venta : ventas) {
            if (!venta.pagoRealizado) {
                double monto = venta.calcularTotalConDescuento();
                totalRecaudado += monto;
                venta.pagoRealizado = true;
                System.out.println("Cobrada venta " + venta.idVenta + " por $" + monto);
            }
        }
        System.out.println("Total recaudado: $" + totalRecaudado);
    }
}