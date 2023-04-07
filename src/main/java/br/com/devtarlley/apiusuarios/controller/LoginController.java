package br.com.devtarlley.apiusuarios.controller;

import br.com.devtarlley.apiusuarios.config.security.JWTResponse;
import br.com.devtarlley.apiusuarios.config.security.TokenService;
import br.com.devtarlley.apiusuarios.dto.LoginDTO;
import br.com.devtarlley.apiusuarios.model.Usuario;
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
    private final TokenService tokenService;

    public LoginController(AuthenticationManager manager, TokenService tokenService) {
        this.manager = manager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<JWTResponse> login( @RequestBody @Valid LoginDTO loginDTO){
            var authenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.email(),loginDTO.senha());
        Authentication authentication = manager.authenticate(authenticationToken);

        String tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok().body(new JWTResponse(tokenJWT));
    }
}
