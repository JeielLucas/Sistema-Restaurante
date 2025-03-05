package com.jeiel.restaurant.services;

import com.jeiel.restaurant.entity.user.User;
import com.jeiel.restaurant.entity.user.UserDetailsImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CookieServiceImpl implements CookieService {
    private final TokenProviderServiceImpl tokenProviderServiceImpl;

    public CookieServiceImpl(TokenProviderServiceImpl tokenProviderServiceImpl) {
        this.tokenProviderServiceImpl = tokenProviderServiceImpl;
    }

    @Override
    public String findCookieValue(HttpServletRequest request, String name){
        Cookie[] cookies = request.getCookies();

        if(cookies == null){
            return null;
        }

        for(Cookie cookie : cookies){
            if(cookie.getName().equals(name)){
                return cookie.getValue();
            }
        }

        return null;
    }

    @Override
    public void generateJWTandAddCookiesToResponse(User user, HttpServletResponse response, String name, int maxAge, boolean secure, boolean httpOnly, int duration){
        UserDetailsImpl userDetails = new UserDetailsImpl(user);

        String token = tokenProviderServiceImpl.generateToken(userDetails, duration);

        Cookie cookie = new Cookie(name, token);

        cookie.setMaxAge(maxAge);
        cookie.setHttpOnly(httpOnly);
        cookie.setSecure(secure);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    @Override
    public ResponseEntity clearCookies(HttpServletResponse response){
        Cookie cookie = new Cookie("access_token", null);
        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setSecure(true);
        response.addCookie(cookie);

        Cookie cookieRefresh = new Cookie("refresh_token", null);
        cookieRefresh.setMaxAge(0);
        cookieRefresh.setHttpOnly(true);
        cookieRefresh.setPath("/");
        cookieRefresh.setSecure(true);
        response.addCookie(cookieRefresh);

        return ResponseEntity.status(HttpStatus.OK).body("Cookies limpos com sucesso");
    }
}
