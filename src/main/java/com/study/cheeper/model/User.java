package com.study.cheeper.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

@Entity
public class User implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Email obrigatório")
    @Email(message = "Formato incorreto")
    private String email;

    @NotBlank(message = "Senha obrigatória")
    private String password;

    @NotBlank(message = "Nome obrigatório")
    private String name;

    @NotBlank(message = "Bio obrigatória")
    private String bio;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public void setPassword(String password) { this.password = password; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getBio() { return bio; }

    public void setBio(String bio) { this.bio = bio; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
     return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
