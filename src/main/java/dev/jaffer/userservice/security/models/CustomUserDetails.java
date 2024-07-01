package dev.jaffer.userservice.security.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.jaffer.userservice.models.Role;
import dev.jaffer.userservice.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@JsonDeserialize
@NoArgsConstructor
public class CustomUserDetails implements UserDetails {


    List<GrantedAuthority> authorities;

    private long userId;
    private String password;
    private String username;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;


    public CustomUserDetails(User user) {

        authorities = new ArrayList<>();
        for (Role role : user.getRoles()) {
            authorities.add(new CustomGrantedAuthority(role));
        }
        this.userId = user.getId();
        this.password = user.getPassword();
        this.username = user.getEmail();
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;



    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {


        return this.authorities;
    }


    public long getUserId() {
        return userId;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
            return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired; // we are not checking if the account is expired or not
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked; // we are not checking if the account is locked or not
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;// we are not checking if the credentials are expired or not
    }

    @Override
    public boolean isEnabled() {
        return this.enabled; // we are not checking if the user is enabled or not
    }
}
