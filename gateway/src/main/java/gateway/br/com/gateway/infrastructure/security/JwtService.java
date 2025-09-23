package gateway.br.com.gateway.infrastructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import gateway.br.com.gateway.domain.exception.TokenJwtInvalidoOuExpirado;
import gateway.br.com.gateway.domain.model.usuario.Usuario;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JwtService {
    @Value("${api.security.token.secret}")
    private String secret;

    // Gera token para o usuário
    public String generateToken(Usuario usuario) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("service-gateway")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(generateDateExpires())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("erro ao gerar token jwt " + exception);
        }
    }

    public String verifierToken(String tokenJwt) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("service-gateway")
                    .build()
                    .verify(tokenJwt)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new TokenJwtInvalidoOuExpirado("Token JWT " + tokenJwt + " inválido ou expirado!");
        }
    }

    /*
    * Metodo para gerar um tempo de 2hrs para o token expirar*/
    private Instant generateDateExpires() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
