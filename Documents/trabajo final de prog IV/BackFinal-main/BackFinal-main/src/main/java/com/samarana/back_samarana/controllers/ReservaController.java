package com.samarana.back_samarana.controllers;

import com.samarana.back_samarana.dto.ErrorResponse;
import com.samarana.back_samarana.entities.Reserva;
import com.samarana.back_samarana.services.ReservaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Reservas", description = "Gestión de reservas de alojamiento")
@RestController
@RequestMapping("/api/reservas")
@CrossOrigin(origins = "*")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @Operation(
            summary = "Listar todas las reservas",
            description = "Retorna una lista con todas las reservas registradas en el sistema"
    )
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    @GetMapping("/listar")
    public ResponseEntity<List<Reserva>> listar() {
        return ResponseEntity.ok(reservaService.listarTodas());
    }

    @Operation(
            summary = "Guardar una nueva reserva",
            description = "Recibe los datos de una reserva y la registra en el sistema"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reserva guardada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error en los datos enviados",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@RequestBody Reserva reserva) {
        try {
            Reserva reservaGuardada = reservaService.guardar(reserva);
            return ResponseEntity.ok(reservaGuardada);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(
            summary = "Cambiar el estado de una reserva",
            description = "Actualiza el estado de una reserva existente según su ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Estado actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "Reserva no encontrada o datos inválidos",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/cambiar-estado/{id}")
    public ResponseEntity<?> cambiarEstado(
            @Parameter(description = "ID de la reserva a modificar", example = "1")
            @PathVariable Long id,
            @Parameter(description = "ID del nuevo estado", example = "2")
            @RequestParam Long nuevoEstado
    ) {
        try {
            Reserva reservaActualizada = reservaService.cambiarEstado(id, nuevoEstado);
            return ResponseEntity.ok(reservaActualizada);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}