package dev.jaffer.userservice.services;


import at.favre.lib.crypto.bcrypt.BCrypt;
import dev.jaffer.userservice.dtos.LoginRequestDto;
import dev.jaffer.userservice.dtos.UserDto;
import dev.jaffer.userservice.exceptions.PasswordNotMatchedException;
import dev.jaffer.userservice.exceptions.UserAlreadyExists;
import dev.jaffer.userservice.exceptions.UserDoesNotExist;
import dev.jaffer.userservice.models.Session;
import dev.jaffer.userservice.models.SessionStatus;
import dev.jaffer.userservice.models.User;
import dev.jaffer.userservice.repositories.SessionRepository;
import dev.jaffer.userservice.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMapAdapter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder ;
    private SessionRepository sessionRepository;

    public AuthService(UserRepository userRepository, SessionRepository sessionRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
        this.sessionRepository = sessionRepository;
    }

    public User signUp(String email, String password) throws UserAlreadyExists {

        Optional<User> userOptional = Optional.ofNullable(userRepository.findByEmail(email));

        if (userOptional.isPresent()) {
            throw new UserAlreadyExists("User already exists");
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));

        User savedUser = userRepository.save(user);

        return savedUser;

    }
    public ResponseEntity<UserDto> login(String email, String password) throws UserDoesNotExist, PasswordNotMatchedException {

        Optional<User> userOptional = Optional.ofNullable(userRepository.findByEmail(email));

        if (userOptional.isEmpty()) {
            throw new UserDoesNotExist("User does not exist");
        }
        User user = userOptional.get();

        if(!bCryptPasswordEncoder.matches(password, user.getPassword())){
            throw new PasswordNotMatchedException("Password does not match");
        }

        //token genetrate us jwtkit
        //create claims map
        Map<String,Object> claims = new HashMap<>();

        claims.put("userId", user.getId());
        claims.put("email", user.getEmail());
        claims.put("roles", user.getRoles());

        //generate jws
        String jws = Jwts.builder()
                .setClaims(claims)
                .compact();



        //prepare headers for response entity multi value map adapter
        MultiValueMapAdapter<String, String> headers = new MultiValueMapAdapter<>(new HashMap<>());
        headers.add("AUTH_TOKEN", jws);

        //create SEssion
        Session session = new Session();
        session.setToken(jws);
        session.setUser(user);
        session.setStatus(SessionStatus.ACTIVE);

        sessionRepository.save(session);

        //return response entity with headers
        UserDto userDto = UserDto.from(user);
        ResponseEntity<UserDto> responseEntity = new ResponseEntity<>(
                userDto,
                headers,
                HttpStatus.OK
        );

        return responseEntity;
    }

    public SessionStatus validateSession(String token) {

        Optional<Session> sessionOptional = Optional.ofNullable(sessionRepository.findByToken(token));

        if (sessionOptional.isEmpty()) {
            return SessionStatus.INVALID;
        }
        Session session = sessionOptional.get();
        if (session.getStatus() == SessionStatus.LOGGED_OUT) {
            return SessionStatus.LOGGED_OUT;
        }
        return SessionStatus.ACTIVE;
    }

    public void logout(String token) {

        Optional<Session> sessionOptional = Optional.ofNullable(sessionRepository.findByToken(token));
        if (sessionOptional.isEmpty()) {
            return;
        }
        Session session = sessionOptional.get();
        session.setStatus(SessionStatus.LOGGED_OUT);
        sessionRepository.save(session);

    }
}
