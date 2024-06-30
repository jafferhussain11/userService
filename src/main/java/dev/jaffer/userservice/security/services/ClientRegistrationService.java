//package dev.jaffer.userservice.security.services;
//
//import dev.jaffer.userservice.security.dtos.ClientDto;
//import dev.jaffer.userservice.security.repositories.JpaRegisteredClientRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.core.AuthorizationGrantType;
//import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
//import org.springframework.security.oauth2.core.oidc.OidcScopes;
//import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
//import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
//import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
//import org.springframework.stereotype.Service;
//
//import java.util.UUID;
//
//@Service
//public class ClientRegistrationService {
//
//        @Autowired
//        private PasswordEncoder passwordEncoder;
//
//        @Autowired
//        private JpaRegisteredClientRepository jpaRegisteredClientRepository;
//
//        public ClientRegistrationService(JpaRegisteredClientRepository jpaRegisteredClientRepository) {
//                this.jpaRegisteredClientRepository = jpaRegisteredClientRepository;
//        }
//
//
//        public ClientDto registerClient(ClientDto clientDto) {
//
//
//        // register client ONLY profile SCOPES , note : THIS IS A TEST CLIENT FOR DEMO PURPOSES
//                RegisteredClient client = RegisteredClient.withId(UUID.randomUUID().toString())
//                .clientId(clientDto.getClientId())
//                .clientSecret(clientDto.getClientSecret())
//                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
//                .redirectUri(clientDto.getRedirectUri())
//                .postLogoutRedirectUri("http://127.0.0.1:8080/")
//                .scope(OidcScopes.OPENID)
//                .scope(OidcScopes.PROFILE)
//                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
//                .build();
//
//                jpaRegisteredClientRepository.save(client);
//
//                return clientDto;
//        }
//}
