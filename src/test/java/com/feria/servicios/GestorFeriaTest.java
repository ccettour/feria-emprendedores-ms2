package com.feria.servicios;
import com.feria.modelos.Venta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GestorFeriaTest {

    private GestorFeria gestor;

    @BeforeEach
    void setUp() {
        gestor = new GestorFeria();
    }

    //registrarEmprendedorConProductos 

    @Test
    void registrarEmprendedor_conDatosValidos_agregaEmprendedor() {
        gestor.registrarEmprendedorConProductos(
                "Laura Ruiz", "E01", "35121234", "laura@mail.com", "ropa",
                List.of("Remera"), List.of(500.0), List.of(10)
        );
        assertEquals(1, gestor.emprendedores.size());
    }

    @Test
    void registrarEmprendedor_conEmailInvalido_noAgregaEmprendedor() {
        gestor.registrarEmprendedorConProductos(
                "Laura Ruiz", "E01", "35121234", "emailsinarroba", "ropa",
                List.of("Remera"), List.of(500.0), List.of(10)
        );
        assertEquals(0, gestor.emprendedores.size());
    }

    @Test
    void registrarEmprendedor_conNombreCorto_noAgregaEmprendedor() {
        gestor.registrarEmprendedorConProductos(
                "L", "E01", "35121234", "laura@mail.com", "ropa",
                List.of("Remera"), List.of(500.0), List.of(10)
        );
        assertEquals(0, gestor.emprendedores.size());
    }

    //registrarVenta

    @Test
    void registrarVenta_conStockSuficiente_descuentaStock() {
        gestor.registrarEmprendedorConProductos(
                "Laura Ruiz", "E01", "35121234", "laura@mail.com", "ropa",
                List.of("Remera"), List.of(500.0), List.of(10)
        );
        gestor.registrarVenta("V01", "E01", "Remera", 3, 500.0, "2025-01-01");
        assertEquals(7, gestor.productos.get(0).stock);
    }

    @Test
    void registrarVenta_conStockInsuficiente_noRegistraVenta() {
        gestor.registrarEmprendedorConProductos(
                "Laura Ruiz", "E01", "35121234", "laura@mail.com", "ropa",
                List.of("Remera"), List.of(500.0), List.of(2)
        );
        gestor.registrarVenta("V01", "E01", "Remera", 5, 500.0, "2025-01-01");
        assertEquals(0, gestor.ventas.size());
    }

    @Test
    void registrarVenta_conProductoInexistente_noRegistraVenta() {
        gestor.registrarVenta("V01", "E99", "ProductoFalso", 1, 100.0, "2025-01-01");
        assertEquals(0, gestor.ventas.size());
    }

    //getEmprendedoresConStockBajo

    @Test
    void getEmprendedoresConStockBajo_conStockMenorACinco_retornaEmprendedor() {
        gestor.registrarEmprendedorConProductos(
                "Laura Ruiz", "E01", "35121234", "laura@mail.com", "ropa",
                List.of("Remera"), List.of(500.0), List.of(2)
        );
        assertEquals(1, gestor.getEmprendedoresConStockBajo().size());
    }

    @Test
    void getEmprendedoresConStockBajo_conStockNormal_retornaListaVacia() {
        gestor.registrarEmprendedorConProductos(
                "Laura Ruiz", "E01", "35121234", "laura@mail.com", "ropa",
                List.of("Remera"), List.of(500.0), List.of(10)
        );
        assertTrue(gestor.getEmprendedoresConStockBajo().isEmpty());
    }

    // SPY manual de Venta, tal como muestra la diapo
static class VentaSpy extends Venta {
    boolean calcularTotalFueLlamado = false;

    public VentaSpy(String idVenta, String empId, String nombre, int cantidad, double precio, String fecha) {
        super(idVenta, empId, nombre, cantidad, precio, fecha);
    }

    @Override
    public double calcularTotalConDescuento() {
        calcularTotalFueLlamado = true;
        return super.calcularTotalConDescuento();
    }
}

@Test
void procesarVentas_conVentaPendiente_llamaAlCalculo() {
    VentaSpy spy = new VentaSpy("V01", "E01", "Remera", 2, 500.0, "2025-01-01");
    spy.pagoRealizado = false;

    gestor.ventas.add(spy);
    gestor.procesarVentasPendientesYCobrar();

    assertTrue(spy.calcularTotalFueLlamado);
}

@Test
void procesarVentas_conVentaYaPagada_noLlamaAlCalculo() {
    VentaSpy spy = new VentaSpy("V01", "E01", "Remera", 2, 500.0, "2025-01-01");
    spy.pagoRealizado = true;

    gestor.ventas.add(spy);
    gestor.procesarVentasPendientesYCobrar();

    assertFalse(spy.calcularTotalFueLlamado);
}
}