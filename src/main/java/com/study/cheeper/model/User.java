package com.study.cheeper.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
public class User implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String password;
    private String name;
    private String bio;
    private String image;
    private LocalDate created = LocalDate.now();
    private String profileName;

    @ManyToMany
    @JoinTable(name="relationship",
            joinColumns=@JoinColumn(name="follower_id"),
            inverseJoinColumns=@JoinColumn(name="followed_id"))
    private List<User> following = new ArrayList<>();

    public List<User> getFollowing() { return Collections.unmodifiableList(following); }

    public void follow(User followed) { this.following.add(followed); }

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
     return Collections.emptyList();
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
