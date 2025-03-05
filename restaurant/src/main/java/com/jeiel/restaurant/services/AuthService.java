package com.jeiel.restaurant.services;

import com.jeiel.restaurant.dtos.ApiResponseDTO;
import com.jeiel.restaurant.dtos.LoginRequestDTO;
import com.jeiel.restaurant.dtos.RegisterRequestDTO;
import com.jeiel.restaurant.entity.user.User;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AuthService {

    ResponseEntity<ApiResponseDTO> login(LoginRequestDTO loginRequestDTO, HttpServletResponse response);

    ResponseEntity register(RegisterRequestDTO registerRequestDTO, HttpServletResponse response);

    void activate(String token);

    List<User> logout();

}
