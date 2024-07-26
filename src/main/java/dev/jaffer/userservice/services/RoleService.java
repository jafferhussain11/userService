package dev.jaffer.userservice.services;

import dev.jaffer.userservice.dtos.RoleRequestDto;
import dev.jaffer.userservice.exceptions.RoleNotFoundException;
import dev.jaffer.userservice.exceptions.UserDoesNotExist;
import dev.jaffer.userservice.models.Role;
import dev.jaffer.userservice.models.User;
import dev.jaffer.userservice.repositories.RoleRepository;
import dev.jaffer.userservice.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class RoleService {

    private RoleRepository roleRepository;
    private UserRepository userRepository;

    public RoleService(RoleRepository roleRepository,UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository=userRepository;
    }
    public Role createRole(String name) {

        Role role = new Role(name);
        return roleRepository.save(role);

    }

    public Role setRole(Long userId, RoleRequestDto roleRequestDto) throws UserDoesNotExist, RoleNotFoundException {

        Optional<User> userOptional = userRepository.findById(userId);

        if(userOptional.isEmpty()) {
            throw new UserDoesNotExist("User does not exist");
        }

        User user = userOptional.get();

        Set<Role> roles = user.getRoles();

        Optional<Role> roleToAssign = roleRepository.findByName(roleRequestDto.getName());

        if(userOptional.isEmpty()) {
            throw new RoleNotFoundException("Role does not exist");
        }
        Role role = roleToAssign.get();
        roles.add(role);

        //save user

        userRepository.save(user);

        return role;



    }
}
