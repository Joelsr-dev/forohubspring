package com.alurachallenge.forohub.infra.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.alurachallenge.forohub.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String apiSecret;

    public String generarToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("Forohub")
                    .withSubject(usuario.getLogin())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFechasExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException();
        }
    }

    /// Codigo corregido ChatGPT
    public String getSubject(String token) {

        //Adicion de alura
        if (token == null) {
            throw new RuntimeException();
        }

        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret); // Clave secreta para firmar y verificar tokens
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("Forohub")
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token); // Verificamos el token

            String subject = decodedJWT.getSubject(); // Obtenemos el 'subject' del token
            if (subject == null || subject.isEmpty()) {
                throw new RuntimeException("El subject del token es inválido o está vacío");
            }
            return subject;

        } catch (JWTVerificationException exception) {
            // Error relacionado con la verificación del token (firma inválida, token expirado, etc.)
            throw new RuntimeException("Error verificando el token JWT: " + exception.getMessage(), exception);
        }
    }

    private Instant generarFechasExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }
}

