package com.jeiel.restaurant.dtos;

import com.jeiel.restaurant.enums.UserRole;

import java.time.Instant;

public record UserResponseDTO(String email, UserRole role, Instant createdAt) {
}