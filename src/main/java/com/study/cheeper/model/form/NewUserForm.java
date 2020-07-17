package com.study.cheeper.model.form;

import com.study.cheeper.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class NewUserForm {

    @NotBlank(message = "Email obrigatório")
    @Email(message = "Formato incorreto")
    private String email;

    @NotBlank(message = "Senha obrigatória")
    private String password;

    @NotBlank(message = "Nome obrigatório")
    private String name;

    @NotBlank(message = "Bio obrigatória")
    private String bio;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public User toUser(PasswordEncoder passwordEncoder) {
        User user = new User();
        user.setName(this.name);
        user.setEmail(this.email);
        user.setPassword(passwordEncoder.encode(this.password));
        user.setBio(this.bio);
        return user;
    }
}
