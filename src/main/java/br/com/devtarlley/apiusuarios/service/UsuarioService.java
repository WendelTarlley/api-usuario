package br.com.devtarlley.apiusuarios.service;

import br.com.devtarlley.apiusuarios.model.Usuario;
import br.com.devtarlley.apiusuarios.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> buscarTodosUsuarios(){
        return usuarioRepository.findAll();
    }
}
