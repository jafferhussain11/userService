package dev.jaffer.userservice.controllers;

import dev.jaffer.userservice.dtos.LoginRequestDto;
import dev.jaffer.userservice.dtos.UserDto;
import dev.jaffer.userservice.models.Session;
import dev.jaffer.userservice.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody LoginRequestDto loginRequest) {

        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        UserDto userDto = authService.signUp(email, password);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<Session> login(@RequestBody LoginRequestDto loginRequestDto) {

        String email = loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();
        Session session = authService.login(email, password);
        return ResponseEntity.ok(session);
    }

    @PostMapping("/validateSession")

    public ResponseEntity<Boolean> validateSession(@RequestBody String token) {
        boolean isValid = authService.validateSession(token);
        return ResponseEntity.ok(isValid);
    }
}

