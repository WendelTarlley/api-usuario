package br.com.devtarlley.apiusuarios.service.security;

import br.com.devtarlley.apiusuarios.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String SECRET;
   public String gerarToken(Usuario usuario){
        try {
            var algorithm = Algorithm.HMAC256(SECRET);
            return JWT.create()
                    .withIssuer("api-usuarios")
                    .withIssuedAt(Instant.now())
                    .withExpiresAt(dataExpricacao())
                    .withSubject(usuario.getEmail())
                    .withClaim("id",usuario.getId().toString())
                    .withClaim("Permissoes",usuario.getAuthorities().toString())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token jwt",exception);
        }
    }
    public String getUsuarioToken(String token){

        DecodedJWT decodedJWT;
        try {
            var algorithm = Algorithm.HMAC256(SECRET);
            return JWT.require(algorithm)
                    .withIssuer("api-usuarios")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token JWT inválido ou expirado");
        }
    }
    
    private Instant dataExpricacao() {
       return LocalDateTime.now().plusHours(5).toInstant(ZoneOffset.of("-03:00"));
    }
}
