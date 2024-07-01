package dev.jaffer.userservice.security.controllers;

import dev.jaffer.userservice.security.dtos.ClientDto;
import dev.jaffer.userservice.security.services.ClientRegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientRegistrationToDB {

    ClientRegistrationService clientRegistrationService;

    public ClientRegistrationToDB(ClientRegistrationService clientRegistrationService) {
        this.clientRegistrationService = clientRegistrationService;
    }

    @PostMapping("/registerClient")
    ResponseEntity<ClientDto> registerClient(@RequestBody ClientDto clientDto) {

        return new ResponseEntity<>(clientRegistrationService.registerClient(clientDto), HttpStatus.OK);

    }
}
