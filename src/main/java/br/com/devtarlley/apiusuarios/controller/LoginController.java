package br.com.devtarlley.apiusuarios.controller;

import br.com.devtarlley.apiusuarios.dto.LoginDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final AuthenticationManager manager;

    public LoginController(AuthenticationManager manager) {
        this.manager = manager;
    }

    @PostMapping
    public ResponseEntity<String> login( @RequestBody @Valid LoginDTO loginDTO){
            var token = new UsernamePasswordAuthenticationToken(loginDTO.email(),loginDTO.senha());
        Authentication authentication = manager.authenticate(token);
        return ResponseEntity.ok().build();
    }
}
