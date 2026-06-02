package com.samarana.back_samarana.controllers;

import com.samarana.back_samarana.dto.LoginRequest;
import com.samarana.back_samarana.dto.LoginResponse;
import com.samarana.back_samarana.entities.Administrador;
import com.samarana.back_samarana.entities.Usuario;
import com.samarana.back_samarana.repositories.AdministradorRepository;
import com.samarana.back_samarana.repositories.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AdministradorRepository administradorRepository;
    private final UsuarioRepository usuarioRepository;

    public AuthController(AdministradorRepository administradorRepository, UsuarioRepository usuarioRepository) {
        this.administradorRepository = administradorRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        // Buscar administrador
        var adminOpt = administradorRepository.findByUsuarioAndContrasena(request.usuario(), request.contrasena());

        if(adminOpt.isPresent()) {
            Administrador admin = adminOpt.get();
            String token = crearTokenDemo(
                    admin.getUsuario(),
                    "ADMIN"
            );

            return ResponseEntity.ok(
                    new LoginResponse(
                            token,
                            "ADMIN",
                            admin.getUsuario()
                    )
            );
        }

        // Buscar usuario normal
        var usuarioOpt = usuarioRepository
                .findByUsuarioAndContrasena(
                        request.usuario(),
                        request.contrasena()
                );

        if(usuarioOpt.isPresent()) {

            Usuario usuario = usuarioOpt.get();

            String token = crearTokenDemo(
                    usuario.getUsuario(),
                    "USER"
            );

            return ResponseEntity.ok(
                    new LoginResponse(
                            token,
                            "USER",
                            usuario.getUsuario()
                    )
            );
        }

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("Usuario o contraseña incorrectos.");
    }

    private String crearTokenDemo(String usuario, String rol) {
        String header = "{\"alg\":\"none\",\"typ\":\"JWT\"}";
        String payload =
                "{\"sub\":\"" + usuario +
                        "\",\"rol\":\"" + rol + "\"}";

        return encodeBase64(header)
                + "."
                + encodeBase64(payload)
                + ".firma-demo";
    }

    private String encodeBase64(String value) {
        return Base64.getEncoder()
                .encodeToString(value.getBytes(StandardCharsets.UTF_8));
    }
}