package com.jeiel.restaurant.dtos;

public record ApiResponseDTO<T>(boolean success, T data, String message){}