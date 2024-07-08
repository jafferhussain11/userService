package dev.jaffer.userservice.repositories;

import dev.jaffer.userservice.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {


    Optional<Role> findByName(String user);
}
