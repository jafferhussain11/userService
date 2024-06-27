package dev.jaffer.userservice.dtos;

import dev.jaffer.userservice.models.Session;
import dev.jaffer.userservice.models.SessionStatus;
import dev.jaffer.userservice.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ValidateTokenResponseDto {

    private SessionStatus status;
    private UserDto user;
}
