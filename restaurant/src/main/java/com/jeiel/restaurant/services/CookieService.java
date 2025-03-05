package com.jeiel.restaurant.services;

import com.jeiel.restaurant.entity.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

public interface CookieService {
    void generateJWTandAddCookiesToResponse(User user, HttpServletResponse response, String name, int maxAge, boolean secure, boolean httpOnly, int duration);

    String findCookieValue(HttpServletRequest request, String name);

    ResponseEntity<Object> clearCookies(HttpServletResponse response);
}
