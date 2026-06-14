package com.feria.modelos;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class EmprendedorFactoryTest {

    @Test
    void crearEmprendedor_conDatosValidos_retornaEmprendedorConProductos() {
        Emprendedor e = EmprendedorFactory.crearEmprendedor(
                "Carlos Diaz", "E01", "35121234", "carlos@mail.com", "tecnologia",
                List.of("Mouse", "Teclado"), List.of(1500.0, 2000.0), List.of(10, 5)
        );
        assertNotNull(e);
        assertEquals(2, e.productos.size());
        assertEquals("Carlos Diaz", e.getNombre());
    }

    @Test
    void crearEmprendedor_conEmailInvalido_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () ->
                EmprendedorFactory.crearEmprendedor(
                        "Carlos Diaz", "E01", "35121234", "emailroto", "tecnologia",
                        List.of("Mouse"), List.of(1500.0), List.of(10)
                )
        );
    }

    @Test
    void crearEmprendedor_conCategoriaNoPermitida_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () ->
                EmprendedorFactory.crearEmprendedor(
                        "Carlos Diaz", "E01", "35121234", "carlos@mail.com", "deportes",
                        List.of("Pelota"), List.of(500.0), List.of(5)
                )
        );
    }

    @Test
    void crearEmprendedor_conTelefonoCorto_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () ->
                EmprendedorFactory.crearEmprendedor(
                        "Carlos Diaz", "E01", "351", "carlos@mail.com", "tecnologia",
                        List.of("Mouse"), List.of(1500.0), List.of(10)
                )
        );
    }

    @Test
    void crearEmprendedor_sinProductos_retornaEmprendedorConListaVacia() {
        Emprendedor e = EmprendedorFactory.crearEmprendedor(
                "Carlos Diaz", "E01", "35121234", "carlos@mail.com", "tecnologia",
                List.of(), List.of(), List.of()
        );
        assertNotNull(e);
        assertTrue(e.productos.isEmpty());
    }
}