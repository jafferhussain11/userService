package dev.jaffer.userservice.services;


import dev.jaffer.userservice.dtos.LoginRequestDto;
import dev.jaffer.userservice.exceptions.UserAlreadyExists;
import dev.jaffer.userservice.models.Session;
import dev.jaffer.userservice.models.User;
import dev.jaffer.userservice.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User signUp(String email, String password) throws UserAlreadyExists {

        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            throw new UserAlreadyExists("User already exists");
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        User savedUser = userRepository.save(user);

        return savedUser;

    }
    public Session login(String email, String password) {

        String token = String.valueOf(Math.random());
        Session session = new Session();
        session.setToken(token);
        return session;
    }

    public boolean validateSession(String token) {
        return false;
    }
}
