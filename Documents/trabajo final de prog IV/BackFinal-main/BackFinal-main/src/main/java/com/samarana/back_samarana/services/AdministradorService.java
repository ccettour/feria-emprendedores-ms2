package com.samarana.back_samarana.services;

import com.samarana.back_samarana.entities.Administrador;
import com.samarana.back_samarana.repositories.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    /**
     * Lógica de autenticación.
     * Busca al usuario y compara la contraseña.
     */
    public boolean autenticar(String nombreUsuario, String passwordIngresada) {

        Optional<Administrador> adminOpt =
                administradorRepository.findByUsuario(nombreUsuario);

        if (adminOpt.isPresent()) {

            Administrador admin = adminOpt.get();

            return admin.getContrasena()
                    .equals(passwordIngresada);
        }
        return false;
    }

    public Optional<Administrador> obtenerPorUsuario(String nombreUsuario) {

        return administradorRepository.findByUsuario(nombreUsuario);

    }

    /**
     * Obtiene la lista de todos los administradores.
     */
    public List<Administrador> obtenerTodos() {
        return administradorRepository.findAll();
    }

    /**
     * Guarda un nuevo administrador o actualiza uno existente.
     */
    public Administrador guardarAdministrador(Administrador administrador) {
        return administradorRepository.save(administrador);
    }
}
