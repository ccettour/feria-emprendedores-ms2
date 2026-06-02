package com.samarana.back_samarana.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Datos necesarios para iniciar sesión")
public record LoginRequest(

        @NotBlank(message = "El usuario es obligatorio")
        @Schema(description = "Nombre de usuario", example = "admin")
        String usuario,

        @NotBlank(message = "La contraseña es obligatoria")
        @Size(min = 4, message = "Mínimo 4 caracteres")
        @Schema(description = "Contraseña del usuario", example = "1234")
        String contrasena

) {}