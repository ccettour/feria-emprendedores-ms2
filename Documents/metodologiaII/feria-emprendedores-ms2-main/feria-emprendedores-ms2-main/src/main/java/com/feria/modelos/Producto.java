package com.feria.modelos;


public class Producto {


    public String nombre;
    public double precio;
    public int stock;
    public String categoriaProducto;  // duplicado con la categoría del emprendedor
    public String emprendedorId;      // referencia inconsistente

    public Producto(String nombre, double precio, int stock, String categoriaProd, String empId) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoriaProducto = categoriaProd;
        this.emprendedorId = empId;
    }


    public double valorTotal() {
        return precio * stock;
    }


    public String mostrar() {
        return nombre + " - $" + precio + " (stock: " + stock + ")";
    }


    public boolean hayStockBajo() {
        if (stock < 5) {
            return true;
        }
        return false;
    }


    public boolean isStockBajo() {
        return stock < 5;
    }
}