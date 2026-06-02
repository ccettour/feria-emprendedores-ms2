package com.samarana.back_samarana.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "alojamiento")
public class Alojamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_alojamiento;

    private String nombre_alojamiento;
    private String descripcion;
    private Double precio_base;
    private Double precio_full;
    private Integer capacidad_max;
}