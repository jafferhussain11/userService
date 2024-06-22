package dev.jaffer.userservice.repositories;

import dev.jaffer.userservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
