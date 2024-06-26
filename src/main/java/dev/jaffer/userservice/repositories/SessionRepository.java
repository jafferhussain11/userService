package dev.jaffer.userservice.repositories;

import dev.jaffer.userservice.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long>
{
    Session save(Session session);

    Session findByToken(String token);

    Session findByUserId(Long userId);
}
