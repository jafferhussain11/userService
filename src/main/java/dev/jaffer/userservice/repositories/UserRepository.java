package dev.jaffer.userservice.repositories;

import dev.jaffer.userservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    public Optional<User> findById(Long id);
}
