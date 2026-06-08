package com.feria;

import com.feria.modelos.Producto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmprendedorFactoryTest {

    @Test
    void entornoConfiguradoCorrectamente() {
        assertTrue(true);
    }

    @Test
    void deberiaIndicarSinStockCuandoStockEsCero() {
        Producto producto = new Producto(
                "Mate", 1500, 0,
                "Artesanias", "EMP001");

        assertEquals("SIN_STOCK",
                producto.obtenerEstadoStock());
    }

    @Test
    void deberiaIndicarStockBajoCuandoHayMenosDeCincoUnidades() {
        Producto producto = new Producto(
                "Mate", 1500, 3,
                "Artesanias", "EMP001");

        assertEquals("STOCK_BAJO",
                producto.obtenerEstadoStock());
    }

    @Test
    void deberiaIndicarStockNormalCuandoHayCincoOMasUnidades() {
        Producto producto = new Producto(
                "Mate", 1500, 10,
                "Artesanias", "EMP001");

        assertEquals("STOCK_NORMAL",
                producto.obtenerEstadoStock());
    }

}
