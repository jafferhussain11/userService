package dev.jaffer.userservice.controllers;

import dev.jaffer.userservice.dtos.UserDto;
import dev.jaffer.userservice.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public ResponseEntity<UserDto> getUserById(Long id) {
        UserDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }


}
