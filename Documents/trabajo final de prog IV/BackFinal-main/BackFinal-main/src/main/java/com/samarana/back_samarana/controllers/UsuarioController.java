package com.samarana.back_samarana.controllers;

import com.samarana.back_samarana.entities.Usuario;
import com.samarana.back_samarana.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/listar")
    public List<Usuario> listar() {
        return usuarioService.listarTodos();
    }

    @PostMapping("/guardar")
    public Usuario guardar(@RequestBody Usuario usuario) {
        return usuarioService.guardar(usuario);
    }

}