package com.study.cheeper.user;

import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class NewUserForm {

    @Email(message = "Formato incorreto")
    @NotBlank(message = "Email obrigatório")
    private String email;

    @NotBlank(message = "Senha obrigatória")
    private String password;

    @NotBlank(message = "Confirme sua senha")
    private String confirmPassword;

    @ProfileNameUnique(message = "Esse nome já está sendo utilizado")
    @NotBlank(message = "Nome do perfil é obrigatório")
    private String profileName;

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

    public String getProfileName() { return profileName; }

    public void setProfileName(String profileName) { this.profileName = profileName; }

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

    public String getConfirmPassword() { return confirmPassword; }

    public void setConfirmPassword(String confirmPassword) { this.confirmPassword = confirmPassword; }

    public User toUser(PasswordEncoder passwordEncoder) {
        User user = new User();
        user.setName(this.name);
        user.setEmail(this.email);
        user.setPassword(passwordEncoder.encode(this.password));
        user.setBio(this.bio);
        user.setProfileName(this.profileName);
        user.setImage("https://cheeper.s3.amazonaws.com/default.png");
        return user;
    }
}
