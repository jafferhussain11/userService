package dev.jaffer.userservice.services;

import dev.jaffer.userservice.models.Role;
import dev.jaffer.userservice.repositories.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    public Role createRole(String name) {

        Role role = new Role(name);
        return roleRepository.save(role);

    }
}
