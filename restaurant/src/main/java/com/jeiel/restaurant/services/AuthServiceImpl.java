package com.jeiel.restaurant.services;

import com.jeiel.restaurant.dtos.ApiResponseDTO;
import com.jeiel.restaurant.dtos.LoginRequestDTO;
import com.jeiel.restaurant.dtos.RegisterRequestDTO;
import com.jeiel.restaurant.dtos.UserResponseDTO;
import com.jeiel.restaurant.entity.user.User;
import com.jeiel.restaurant.enums.UserRole;
import com.jeiel.restaurant.exceptions.InvalidCredentialsException;
import com.jeiel.restaurant.exceptions.MismatchException;
import com.jeiel.restaurant.exceptions.UserAlreadyExistsException;
import com.jeiel.restaurant.exceptions.UserNotFoundException;
import com.jeiel.restaurant.repositories.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final CookieService cookieService;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UserRepository userRepository, TokenService tokenService, CookieService cookieService, JwtService jwtService, BCryptPasswordEncoder bCryptPasswordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.cookieService = cookieService;
        this.jwtService = jwtService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public ResponseEntity<ApiResponseDTO> login(LoginRequestDTO loginRequestDTO, HttpServletResponse response) {
        cookieService.clearCookies(response);

        User user = userExists(loginRequestDTO.email());

        authenticateUser(loginRequestDTO);

        issueJwtCookies(user, response);

        System.out.println("Authenticate and generate token");

        return ResponseEntity.ok(new ApiResponseDTO<>(true, "", "Login efetuado com sucesso"));
    }

    @Override
    public ResponseEntity register(RegisterRequestDTO registerRequestDTO, HttpServletResponse response) {
        cookieService.clearCookies(response);

        validateEmails(registerRequestDTO);
        validatePasswords(registerRequestDTO.password(), registerRequestDTO.confirmPassword());

        User user = userRepository.findUserByEmail(registerRequestDTO.email());

        if(user != null){
            throw new UserAlreadyExistsException("User already exists");
        }

        String encryptedPassword = bCryptPasswordEncoder.encode(registerRequestDTO.password());

        user = new User(registerRequestDTO.name(), registerRequestDTO.email(), encryptedPassword, UserRole.USER);
        user.setCreatedAt(Instant.now());

        userRepository.save(user);

        UserResponseDTO userResponseDTO = new UserResponseDTO(user.getEmail(), user.getRole(), user.getCreatedAt());

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDTO<>(true, userResponseDTO, "Usuário criado com sucesso"));
    }

    @Override
    public void activate(String token) {

    }

    @Override
    public List<User> logout() {
        return userRepository.findAll();
    }

    private User userExists(String email) {
        User user = userRepository.findUserByEmail(email);

        if(user == null){
            throw new UserNotFoundException("User not found");
        }

        return user;
    }

    private void validateEmails(RegisterRequestDTO userDTO){
        if(!userDTO.email().equals(userDTO.confirmEmail())){
            throw new MismatchException("Emails não coincidem");
        }
    }

    private void validatePasswords(String password, String confirmPassword){
        if(!password.equals(confirmPassword)){
            throw new MismatchException("Senhas não coincidem");
        }
    }

    private void authenticateUser(LoginRequestDTO userDTO){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.email(), userDTO.password()));
        }catch(BadCredentialsException e){
            throw new InvalidCredentialsException("Credenciais invalidas");
        }
    }

    private void issueJwtCookies(User user, HttpServletResponse response){
        cookieService.generateJWTandAddCookiesToResponse(user, response, "access_token", 30*60, false, true, 30);

        cookieService.generateJWTandAddCookiesToResponse(user, response, "refresh_token", 3*24*60*60, false, true, 72*60);

    }
}
