package com.samarana.back_samarana.entities;

import jakarta.persistence.*;
        import lombok.Data;

@Entity
@Data
@Table(name = "Administrador")
public class Administrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_admin")
    private Long id_admin;

    @Column(nullable = false, unique = true)
    private String usuario;

    @Column(nullable = false)
    private String contrasena;
    private String email;
}
