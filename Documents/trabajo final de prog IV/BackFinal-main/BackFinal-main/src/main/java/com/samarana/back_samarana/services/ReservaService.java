package com.samarana.back_samarana.services;

import com.samarana.back_samarana.entities.Reserva;
import com.samarana.back_samarana.entities.Usuario;
import com.samarana.back_samarana.entities.EstadoReserva;
import com.samarana.back_samarana.repositories.ReservaRepository;
import com.samarana.back_samarana.repositories.UsuarioRepository;
import com.samarana.back_samarana.repositories.EstadoReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EstadoReservaRepository estadoReservaRepository;

    public List<Reserva> listarTodas() {
        return reservaRepository.findAll();
    }

    public Reserva guardar(Reserva nuevaReserva) throws Exception {
        List<Reserva> reservasExistentes = reservaRepository.findAll().stream()
                .filter(r -> r.getAlojamiento().getId_alojamiento().equals(nuevaReserva.getAlojamiento().getId_alojamiento()))
                .collect(Collectors.toList());

        for (Reserva existente : reservasExistentes) {
            if (nuevaReserva.getFecha_inicio().isBefore(existente.getFecha_fin()) &&
                    existente.getFecha_inicio().isBefore(nuevaReserva.getFecha_fin())) {
                throw new Exception("Lo sentimos, el bungalow ya está ocupado en esas fechas.");
            }
        }

        long noches = ChronoUnit.DAYS.between(nuevaReserva.getFecha_inicio(), nuevaReserva.getFecha_fin());
        if (noches <= 0) throw new Exception("La fecha de salida debe ser posterior a la de entrada.");

        EstadoReserva estadoInicial = estadoReservaRepository.findById(1L)
                .orElseThrow(() -> new Exception("Error interno: No se encontró el estado 'Pendiente'."));
        nuevaReserva.setEstado(estadoInicial);

        if (nuevaReserva.getUsuario() != null) {
            Usuario usuarioGuardado = usuarioRepository.save(nuevaReserva.getUsuario());
            nuevaReserva.setUsuario(usuarioGuardado);
        }

        return reservaRepository.save(nuevaReserva);
    }

    public Reserva cambiarEstado(Long idReserva, Long idNuevoEstado) throws Exception {
        Reserva reserva = reservaRepository.findById(idReserva)
                .orElseThrow(() -> new Exception("No se encontró la reserva con ID: " + idReserva));

        EstadoReserva nuevoEstado = estadoReservaRepository.findById(idNuevoEstado)
                .orElseThrow(() -> new Exception("El estado solicitado no existe."));

        reserva.setEstado(nuevoEstado);
        return reservaRepository.save(reserva);
    }
}