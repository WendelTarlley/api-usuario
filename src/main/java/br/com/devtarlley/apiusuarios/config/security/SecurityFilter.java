package br.com.devtarlley.apiusuarios.config.security;

import br.com.devtarlley.apiusuarios.service.security.TokenService;
import br.com.devtarlley.apiusuarios.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;


    public SecurityFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = getToken(request);
        if (token != null){
            String usuarioToken = tokenService.getUsuarioToken(token);

            UserDetails byEmail = usuarioRepository.findByEmail(usuarioToken);

            var authentication = new UsernamePasswordAuthenticationToken(byEmail,null,byEmail.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request,response);
    }

    private String getToken(HttpServletRequest request) {

        var authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null){
            if(!authorizationHeader.startsWith("Bearer ")){
                throw new RuntimeException("Tipo de token não é Bearer!");
            }
            return authorizationHeader.substring(7);
        }
       return null;
    }
}
