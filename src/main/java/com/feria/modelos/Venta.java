package com.feria.modelos;

public class Venta {

    public String idVenta;
    public String emprendedorId;
    public String productoNombre;
    public int cantidad;
    public double precioUnitario;
    public String fecha;
    public boolean pagoRealizado;

    public Venta(String idVenta, String empId, String prodNombre, int cant, double precioUnit, String fecha) {
        this.idVenta = idVenta;
        this.emprendedorId = empId;
        this.productoNombre = prodNombre;
        this.cantidad = cant;
        this.precioUnitario = precioUnit;
        this.fecha = fecha;
        this.pagoRealizado = false;
    }

    public double calcularTotalConDescuento() {
        double total = cantidad * precioUnitario;

        if (cantidad > 10) {
            total = total * 0.9;
        }

        if (total > 5000) {
            total = total * 0.95;
        }

        return total;
    }

    public void registrarPagoYActualizarStock(Producto p) {
        this.pagoRealizado = true;
        if (p != null) {
            p.stock = p.stock - this.cantidad;
        }
        System.out.println("Pago registrado y stock actualizado");
    }

    public String generarRecibo() {
        String recibo = "=== RECIBO DE VENTA ===\n";
        recibo += "Venta ID: " + idVenta + "\n";
        recibo += "Fecha: " + fecha + "\n";
        recibo += "Producto: " + productoNombre + "\n";
        recibo += "Cantidad: " + cantidad + "\n";
        recibo += "Precio unitario: $" + precioUnitario + "\n";
        recibo += "Total con descuentos: $" + calcularTotalConDescuento() + "\n";
        recibo += "Pago: " + (pagoRealizado ? "Realizado" : "Pendiente") + "\n";
        return recibo;
    }
}