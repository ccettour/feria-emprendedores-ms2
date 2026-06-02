package com.samarana.back_samarana.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Datos necesarios para iniciar sesión")
public record LoginRequest(

        @Schema(description = "Nombre de usuario", example = "admin")
        String usuario,

        @Schema(description = "Contraseña del usuario", example = "1234")
        String contrasena

) {}