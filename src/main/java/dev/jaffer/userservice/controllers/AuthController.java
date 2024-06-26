package dev.jaffer.userservice.controllers;

import dev.jaffer.userservice.dtos.LoginRequestDto;
import dev.jaffer.userservice.dtos.UserDto;
import dev.jaffer.userservice.dtos.ValidateTokenRequestDto;
import dev.jaffer.userservice.exceptions.PasswordNotMatchedException;
import dev.jaffer.userservice.exceptions.UserAlreadyExists;
import dev.jaffer.userservice.exceptions.UserDoesNotExist;
import dev.jaffer.userservice.models.Session;
import dev.jaffer.userservice.models.SessionStatus;
import dev.jaffer.userservice.models.User;
import dev.jaffer.userservice.services.AuthService;
import io.jsonwebtoken.Jwts;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody LoginRequestDto loginRequest) throws UserAlreadyExists {

        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        User user = authService.signUp(email, password);
        return ResponseEntity.ok(UserDto.from(user));
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequestDto loginRequestDto) throws UserDoesNotExist, PasswordNotMatchedException {

        String email = loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();
        return authService.login(email, password);
    }

    @PostMapping("/validateSession")
    public ResponseEntity<SessionStatus> validateSession(@RequestBody ValidateTokenRequestDto validateTokenRequestDto) {
        String token = validateTokenRequestDto.getToken();
        SessionStatus sessionStatus = authService.validateSession(token);
        return ResponseEntity.ok(sessionStatus);

    }
}

