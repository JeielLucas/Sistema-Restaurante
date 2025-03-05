package com.jeiel.restaurant.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.jeiel.restaurant.entity.user.UserDetailsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@Slf4j
public class TokenProviderServiceImpl implements TokenProvider{
    @Override
    public String generateToken(UserDetailsImpl user, int durationInMinutes) {
        String secretKey = "Teste123";
        String issuer = "api_restaurant";
        try{
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            System.out.println(expirationDate(durationInMinutes));
            return JWT.create()
                    .withIssuer(issuer)
                    .withSubject(user.getUsername())
                    .withIssuedAt(Instant.now())
                    .withExpiresAt(expirationDate(durationInMinutes))
                    .sign(algorithm);
        }catch (JWTCreationException ex){
            throw new JWTCreationException("Erro ao gerar o token: ", ex);
        }
    }

    @Override
    public String validateToken(String token) {
        String secretKey = "Teste123";
        String issuer = "api_restaurant";
        try{
            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            return JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException ex){
            throw new JWTVerificationException("Erro ao verificar o token: " + ex.getMessage(), ex);
        }
    }

    private Instant expirationDate(int durationInMinutes){
        return Instant.now().plus(durationInMinutes, ChronoUnit.MINUTES);
    }
}
