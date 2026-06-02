package com.samarana.back_samarana.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Modelo estándar para respuestas de error de la API")
public class ErrorResponse {

    @Schema(description = "Código de error HTTP", example = "400")
    private int status;

    @Schema(description = "Mensaje descriptivo del error", example = "Datos inválidos")
    private String message;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() { return status; }
    public String getMessage() { return message; }
}