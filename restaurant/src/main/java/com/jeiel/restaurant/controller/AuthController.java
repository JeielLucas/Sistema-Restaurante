package com.jeiel.restaurant.controller;

import com.jeiel.restaurant.dtos.ApiResponseDTO;
import com.jeiel.restaurant.dtos.LoginRequestDTO;
import com.jeiel.restaurant.dtos.RegisterRequestDTO;
import com.jeiel.restaurant.entity.user.User;
import com.jeiel.restaurant.repositories.UserRepository;
import com.jeiel.restaurant.services.AuthServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthServiceImpl authService;

    public AuthController(AuthServiceImpl authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity Register(@RequestBody RegisterRequestDTO newUser, HttpServletResponse response){
        return authService.register(newUser, response);
    }

    @PostMapping("/auth")
    public ResponseEntity<ApiResponseDTO> Login(@RequestBody LoginRequestDTO user, HttpServletResponse response){
        return authService.login(user, response);
    }

    @GetMapping("/activate/{token}")
    public void Activate(@PathVariable String token){
        authService.activate(token);
    }

    @GetMapping("/teste")
    public List<User> list(){
        return authService.logout();
    }
}
