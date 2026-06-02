package com.samarana.back_samarana.services;

import com.samarana.back_samarana.entities.Usuario;
;
import com.samarana.back_samarana.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<Usuario> obtenerPorUsuario(String nombreUsuario) {

        return usuarioRepository.findByUsuario(nombreUsuario);
    }
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }
    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }
}