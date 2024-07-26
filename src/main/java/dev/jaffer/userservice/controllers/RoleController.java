package dev.jaffer.userservice.controllers;

import dev.jaffer.userservice.dtos.RoleRequestDto;
import dev.jaffer.userservice.exceptions.RoleNotFoundException;
import dev.jaffer.userservice.exceptions.UserDoesNotExist;
import dev.jaffer.userservice.models.Role;
import dev.jaffer.userservice.models.User;
import dev.jaffer.userservice.services.RoleService;
import dev.jaffer.userservice.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private RoleService roleService;


    public RoleController(RoleService roleService) {
        this.roleService = roleService;

    }

    @PostMapping("/createRole")
    public ResponseEntity<Role> createRole(@RequestBody RoleRequestDto request) {
        Role savedRole = roleService.createRole(request.getName());
        return ResponseEntity.ok(savedRole);
    }

    @PostMapping("/setRole/{userId}")
    public ResponseEntity<Role> setRole(@PathVariable("userId") Long userId,@RequestBody RoleRequestDto roleRequestDto) throws UserDoesNotExist, RoleNotFoundException {

        Role role = roleService.setRole(userId,roleRequestDto);
        return ResponseEntity.ok(role);


    }
}
