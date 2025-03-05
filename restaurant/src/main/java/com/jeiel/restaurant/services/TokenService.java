package com.jeiel.restaurant.services;

import com.jeiel.restaurant.entity.user.User;

public interface TokenService {
    void generateUUIDToken(String type, User user);

    String extractGoogleEmail(String token);

    User validateAndGetUserByToken(String type, String token);
}
