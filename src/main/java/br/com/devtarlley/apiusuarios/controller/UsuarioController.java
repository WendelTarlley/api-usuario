package br.com.devtarlley.apiusuarios.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class UsuarioController {

    @GetMapping
    public ResponseEntity<String> teste(){
        return ResponseEntity.ok().build();
    }
}
