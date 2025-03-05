package com.jeiel.restaurant.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

public interface JwtService {
    void authenticateUserFromToken(String token);

    ResponseEntity<Object> validateAccessToken(HttpServletRequest request, HttpServletResponse response);
}
