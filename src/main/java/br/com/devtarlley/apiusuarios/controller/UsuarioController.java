package br.com.devtarlley.apiusuarios.controller;

import br.com.devtarlley.apiusuarios.model.Usuario;
import br.com.devtarlley.apiusuarios.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getTodosUsuarios(){
        return ResponseEntity.ok().body(usuarioService.buscarTodosUsuarios());
    }
}
