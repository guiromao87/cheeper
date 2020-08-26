package com.study.cheeper.user;

import com.study.cheeper.login.UserSummary;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String password;
    private String name;
    private String bio;
    private String image;
    private LocalDate created = LocalDate.now();
    private String profileName;
    private boolean verifiedEmail;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public void setPassword(String password) { this.password = password; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getBio() { return bio; }

    public void setBio(String bio) { this.bio = bio; }

    public String getImage() { return image; }

    public void setImage(String image) { this.image = image; }

    public LocalDate getCreated() { return created; }

    public String getProfileName() { return profileName; }

    public void setProfileName(String profileName) { this.profileName = profileName; }

    public boolean isVerifiedEmail() { return verifiedEmail; }

    public void setVerifiedEmail(boolean verifiedEmail) { this.verifiedEmail = verifiedEmail; }

    public boolean isNotTheSameAs(User loggedUser) {
        return !this.getId().equals(loggedUser.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return profileName.equals(user.profileName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profileName);
    }

    public UserSummary toUserSummary() { return new UserSummary(this.id, this.profileName, this.email, this.password); }
}
