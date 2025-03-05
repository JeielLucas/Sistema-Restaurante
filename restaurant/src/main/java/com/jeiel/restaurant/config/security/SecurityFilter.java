package com.jeiel.restaurant.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeiel.restaurant.dtos.ErrorResponseDTO;
import com.jeiel.restaurant.exceptions.UnauthorizedException;
import com.jeiel.restaurant.services.CookieServiceImpl;
import com.jeiel.restaurant.services.JwtServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final JwtServiceImpl jwtServiceImpl;
    private final CookieServiceImpl cookieServiceImpl;
    private final ObjectMapper objectMapper;

    public SecurityFilter(JwtServiceImpl jwtServiceImpl, CookieServiceImpl cookieServiceImpl, ObjectMapper objectMapper) {
        this.jwtServiceImpl = jwtServiceImpl;
        this.cookieServiceImpl = cookieServiceImpl;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = cookieServiceImpl.findCookieValue(request, "access_token");
        if(token != null){
            try{
                jwtServiceImpl.validateAccessToken(request, response);
                filterChain.doFilter(request, response);
                return;
            }catch (UnauthorizedException ex){
                handleUnauthorizedException(response, ex);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private void handleUnauthorizedException(HttpServletResponse response,
                                             UnauthorizedException ex) throws IOException {
        cookieServiceImpl.clearCookies(response);
        ErrorResponseDTO<Object> errorResponse = new ErrorResponseDTO<>(
                HttpServletResponse.SC_UNAUTHORIZED,
                "Token inválido ou expirado",
                Collections.singletonList(ex.getMessage())
        );

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        String jsonResponse = objectMapper.writeValueAsString(errorResponse);
        response.getWriter().write(jsonResponse);
    }
}
