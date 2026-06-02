package com.samarana.back_samarana.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_reserva;

    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;
    private Integer cantidad_personas;

    @ManyToOne
    @JoinColumn(name = "id_alojamiento")
    private Alojamiento alojamiento;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_admin")
    private Administrador administrador;

    @ManyToOne
    @JoinColumn(name = "id_estado")
    private EstadoReserva estado;
}