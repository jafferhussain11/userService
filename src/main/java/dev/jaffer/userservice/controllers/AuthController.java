package dev.jaffer.userservice.controllers;

import dev.jaffer.userservice.dtos.*;
import dev.jaffer.userservice.exceptions.PasswordNotMatchedException;
import dev.jaffer.userservice.exceptions.UserAlreadyExists;
import dev.jaffer.userservice.exceptions.UserDoesNotExist;
import dev.jaffer.userservice.models.Session;
import dev.jaffer.userservice.models.SessionStatus;
import dev.jaffer.userservice.models.User;
import dev.jaffer.userservice.services.AuthService;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignupRequestDto signupRequestDto) throws UserAlreadyExists {

        String email = signupRequestDto.getEmail();
        String password = signupRequestDto.getPassword();
        User user = authService.signUp(email, password);
        return ResponseEntity.ok(UserDto.from(user));
    }

    // NOT USED ,SPring security is used for authentication
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequestDto loginRequestDto) throws UserDoesNotExist, PasswordNotMatchedException {

        String email = loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();
        return authService.login(email, password);
    }

    @PostMapping("/validateSession")
    public ResponseEntity<ValidateTokenResponseDto> validateSession(@RequestBody ValidateTokenRequestDto validateTokenRequestDto) {
        String token = validateTokenRequestDto.getToken();
        Long userId = validateTokenRequestDto.getUserId();

        Optional<UserDto> userDto = authService.validate(token, userId);

        if(userDto.isEmpty()) {
            ValidateTokenResponseDto response = new ValidateTokenResponseDto();
            response.setStatus(SessionStatus.INVALID);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        ValidateTokenResponseDto response = new ValidateTokenResponseDto();
        response.setStatus(SessionStatus.ACTIVE);
        response.setUser(userDto.get());
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}

