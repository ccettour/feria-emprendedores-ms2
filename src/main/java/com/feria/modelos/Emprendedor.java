package com.feria.modelos;

import java.util.ArrayList;
import java.util.List;


public class Emprendedor {

    public String n;      // nombre
    public String id;     // identificador
    public String t;      // teléfono
    public String m;      // email
    public String cat;    // categoria: comida, artesania, tecnologia, ropa


    public List<Producto> prods;

    public Emprendedor(String nom, String id, String tel, String mail, String categoria) {
        this.n = nom;
        this.id = id;
        this.t = tel;
        this.m = mail;
        this.cat = categoria;
        this.prods = new ArrayList<>();
    }


    public String mostrarInfoYValidar() {
        String info = "Emprendedor: " + n + "\n";
        info += "ID: " + id + "\n";
        info += "Contacto: " + t + " | " + m + "\n";
        info += "Categoría: " + cat + "\n";

        // VALIDACIONES
        if (n == null || n.length() < 2) {
            info += "⚠️ NOMBRE DEMASIADO CORTO\n";
        }
        if (m == null || !m.contains("@")) {
            info += "⚠️ EMAIL INVÁLIDO\n";
        }
        if (cat == null || (!cat.equals("comida") && !cat.equals("artesania") && 
                           !cat.equals("tecnologia") && !cat.equals("ropa"))) {
            info += "⚠️ CATEGORÍA DESCONOCIDA\n";
        }

        info += "Productos:\n";
        for (Producto p : prods) {
            info += "  - " + p.nombre + " ($" + p.precio + ")\n";
        }

        return info;
    }


    public boolean validarCompleto() {
        boolean valido = true;
        if (n == null || n.length() < 2) valido = false;
        if (m == null || !m.contains("@")) valido = false;
        if (cat == null || (!cat.equals("comida") && !cat.equals("artesania") && 
                           !cat.equals("tecnologia") && !cat.equals("ropa"))) valido = false;
        return valido;
    }


    public String getNombre() {
        return n;
    }

    public void agregarProducto(Producto p) {
        prods.add(p);
    }


    public int calcularValorTotalStock() {
        int total = 0;
        for (Producto p : prods) {
            total += p.precio * p.stock;
        }
        return total;
    }
}