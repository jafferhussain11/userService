package dev.jaffer.userservice.services;

import dev.jaffer.userservice.models.Role;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    public Role createRole(String name) {
        return new Role(name);
    }
}
