package com.jeiel.restaurant.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class JwtServiceImpl implements JwtService {
    @Override
    public void authenticateUserFromToken(String token) {

    }

    @Override
    public ResponseEntity<Object> validateAccessToken(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
