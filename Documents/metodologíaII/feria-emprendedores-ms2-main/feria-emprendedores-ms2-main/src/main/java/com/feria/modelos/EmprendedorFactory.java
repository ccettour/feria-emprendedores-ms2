package com.feria.modelos;

import com.feria.utils.Validadores;
import java.util.List;

public class EmprendedorFactory {

    public static Emprendedor crearEmprendedor(String nombre, String id, String telefono,
                                               String email, String categoria,
                                               List<String> nombresProductos,
                                               List<Double> precios,
                                               List<Integer> stocks) {

        if (!Validadores.emailValido(email)) {
            throw new IllegalArgumentException("Email inválido: " + email);
        }
        if (!Validadores.categoriaPermitida(categoria)) {
            throw new IllegalArgumentException("Categoría no permitida: " + categoria);
        }
        if (!Validadores.telefonoValido(telefono)) {
            throw new IllegalArgumentException("Teléfono inválido: " + telefono);
        }

        Emprendedor emprendedor = new Emprendedor(nombre, id, telefono, email, categoria);

        for (int i = 0; i < nombresProductos.size(); i++) {
            Producto producto = new Producto(
                nombresProductos.get(i),
                precios.get(i),
                stocks.get(i),
                categoria,
                id
            );
            emprendedor.agregarProducto(producto);
        }

        return emprendedor;
    }
}