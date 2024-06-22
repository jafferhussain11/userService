package dev.jaffer.userservice.controllers;

import dev.jaffer.userservice.dtos.RoleRequestDto;
import dev.jaffer.userservice.models.Role;
import dev.jaffer.userservice.services.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody RoleRequestDto request) {
        Role savedRole = roleService.createRole(request.getName());
        return ResponseEntity.ok(savedRole);
    }
}
