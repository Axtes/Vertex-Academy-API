package edu.unijorge.br.Vertex.Academy.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import edu.unijorge.br.Vertex.Academy.domain.model.users.Usuario.Usuario;
import edu.unijorge.br.Vertex.Academy.domain.model.users.Usuario.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Service
public class TokenService {

    private final SecretRepository secretRepository;
    private final UsuarioRepository usuarioRepository;

    public TokenService(SecretRepository secretRepository, UsuarioRepository usuarioRepository) {
        this.secretRepository = secretRepository;
        this.usuarioRepository = usuarioRepository;
    }

    private String getSecret() {
        return secretRepository.findAll().stream().findFirst()
                .map(Secret::getTokenSecret)
                .orElseGet(() -> {

                    Secret secret = new Secret();
                    secret.setTokenSecret(UUID.randomUUID().toString());
                    secretRepository.save(secret);
                    return secret.getTokenSecret();
                });
        }

    public  String gerarToken(Usuario usuario) {
        try{
            var algoritmo = Algorithm.HMAC256(getSecret());
            return JWT.create()
                    .withIssuer("API Vertex Academy")
                    .withSubject(usuario.getEmail())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(tokenExpiracao())
                    .sign(algoritmo);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token JWT", exception);
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256(getSecret());
            return JWT.require(algoritmo)
                    .withIssuer("API Vertex Academy")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw  new RuntimeException("Token inválido ou expirado: " + tokenJWT);
        }
    }

    private Instant tokenExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}

