package dev.jaffer.userservice.repositories;

import dev.jaffer.userservice.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long>
{
}
