package com.jeiel.restaurant.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jeiel.restaurant.entity.user.User;
import com.jeiel.restaurant.exceptions.TokenVerificationException;
import com.jeiel.restaurant.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class TokenServiceImpl implements TokenService{


    private final UserRepository userRepository;

    public TokenServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void generateUUIDToken(String type, User user) {
        String token = UUID.randomUUID().toString();

        //user.setToken(token);
        //user.setTokenType(type);
        //user.setTokenExpiration(LocalDateTime.now().plusMinutes(30));
    }

    @Override
    public String extractGoogleEmail(String token){
        try{
            DecodedJWT decodedJWT = JWT.decode(token);

            return decodedJWT.getClaim("email").asString();
        }catch (JWTDecodeException ex){
            throw new TokenVerificationException(ex.getMessage());
        }
    }

    @Override
    public User validateAndGetUserByToken(String type, String token){
        //User user = userRepository.findByToken(token);
        User user = null;

        if(user == null){
            throw new TokenVerificationException("Token invalido");
        }

       /* if(user.getTokenExpiration().isBefore(LocalDateTime.now())){
            log.error("Token de {} invalido ou expirou as {}", type, user.getTokenExpiration());
            throw new TokenVerificationException("Token expirado");
        }

        if(!user.getTokenType().equals(type)){
            log.error("O token nao é do tipo {}", type);
            throw new TokenVerificationException("Token do tipo incorreto");
        }
        */

        return user;
    }

}