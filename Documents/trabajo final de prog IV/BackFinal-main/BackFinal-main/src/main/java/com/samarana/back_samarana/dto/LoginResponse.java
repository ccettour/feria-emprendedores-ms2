package com.samarana.back_samarana.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta del servidor al iniciar sesión correctamente")
public record LoginResponse(

        @Schema(description = "Token JWT para autenticación", example = "eyJhbGciOiJIUzI1NiJ9...")
        String token,

        @Schema(description = "Rol del usuario autenticado", example = "ADMIN")
        String rol,

        @Schema(description = "Nombre del usuario autenticado", example = "admin")
        String usuario

) {}