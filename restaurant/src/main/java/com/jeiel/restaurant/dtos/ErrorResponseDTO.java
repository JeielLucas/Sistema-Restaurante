package com.jeiel.restaurant.dtos;

public record ErrorResponseDTO<T>(int code, String message, T details){}
