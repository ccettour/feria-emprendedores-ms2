package com.samarana.back_samarana.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;

    private String usuario;
    private String contrasena;
    private String dni;
    private String email;
    private String telefono;
}