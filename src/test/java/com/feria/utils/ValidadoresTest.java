package com.feria.utils;

import com.feria.modelos.Emprendedor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ValidadoresTest {

    //emailValido

    @Test
    void emailValido_conEmailCorrecto_retornaTrue() {
        assertTrue(Validadores.emailValido("vendedor@feria.com"));
    }

    @Test
    void emailValido_sinArroba_retornaFalse() {
        assertFalse(Validadores.emailValido("vendedorferia.com"));
    }

    @Test
    void emailValido_conNull_retornaFalse() {
        assertFalse(Validadores.emailValido(null));
    }

    @Test
    void emailValido_demasiadoCorto_retornaFalse() {
        assertFalse(Validadores.emailValido("a@b"));
    }

    //telefonoValido

    @Test
    void telefonoValido_conOchoDigitos_retornaTrue() {
        assertTrue(Validadores.telefonoValido("35121234"));
    }

    @Test
    void telefonoValido_conMenosDeOchoCaracteres_retornaFalse() {
        assertFalse(Validadores.telefonoValido("3512"));
    }

    @Test
    void telefonoValido_conNull_retornaFalse() {
        assertFalse(Validadores.telefonoValido(null));
    }

    //categoriaPermitida

    @Test
    void categoriaPermitida_conComida_retornaTrue() {
        assertTrue(Validadores.categoriaPermitida("comida"));
    }

    @Test
    void categoriaPermitida_conCategoriaInexistente_retornaFalse() {
        assertFalse(Validadores.categoriaPermitida("electronica"));
    }

    @Test
    void categoriaPermitida_conMayusculas_retornaFalse() {
        assertFalse(Validadores.categoriaPermitida("Comida"));
    }

    // validarPrecioStock

    @Test
    void validarPrecioStock_conValoresValidos_retornaTrue() {
        assertTrue(Validadores.validarPrecioStock(100.0, 10));
    }

    @Test
    void validarPrecioStock_conPrecioCero_retornaFalse() {
        assertFalse(Validadores.validarPrecioStock(0, 10));
    }

    @Test
    void validarPrecioStock_conStockNegativo_retornaFalse() {
        assertFalse(Validadores.validarPrecioStock(100.0, -1));
    }

    //validarEmprendedorCompleto

    @Test
    void validarEmprendedorCompleto_conEmprendedorValido_retornaTrue() {
        Emprendedor e = new Emprendedor("Ana Lopez", "E01", "35121234", "ana@mail.com", "comida");
        assertTrue(Validadores.validarEmprendedorCompleto(e));
    }

    @Test
    void validarEmprendedorCompleto_conNull_retornaFalse() {
        assertFalse(Validadores.validarEmprendedorCompleto(null));
    }

    @Test
    void validarEmprendedorCompleto_conEmailInvalido_retornaFalse() {
        Emprendedor e = new Emprendedor("Ana Lopez", "E01", "35121234", "mailsinarroba", "comida");
        assertFalse(Validadores.validarEmprendedorCompleto(e));
    }

    @Test
    void validarEmprendedorCompleto_conNombreDeUnLetra_retornaFalse() {
        Emprendedor e = new Emprendedor("A", "E01", "35121234", "ana@mail.com", "comida");
        assertFalse(Validadores.validarEmprendedorCompleto(e));
    }
}