package dev.jaffer.userservice.security.models;

import dev.jaffer.userservice.models.Role;
import dev.jaffer.userservice.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    User user;
    public CustomUserDetails(User user) {
        this.user = user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<CustomGrantedAuthority> customGrantedAuthorities= new ArrayList<>();
        for(Role role: user.getRoles()){
            customGrantedAuthorities.add(new CustomGrantedAuthority(role));
        }
        return customGrantedAuthorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // we are not checking if the account is expired or not
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // we are not checking if the account is locked or not
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // we are not checking if the credentials are expired or not
    }

    @Override
    public boolean isEnabled() {
        return true; // we are not checking if the user is enabled or not
    }
}
