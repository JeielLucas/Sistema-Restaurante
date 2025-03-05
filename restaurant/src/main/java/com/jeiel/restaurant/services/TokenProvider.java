package com.jeiel.restaurant.services;

import com.jeiel.restaurant.entity.user.UserDetailsImpl;

public interface TokenProvider {
    String generateToken(UserDetailsImpl user, int durationInMinutes);

    String validateToken(String token);
}
