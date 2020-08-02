package com.study.cheeper.model.dto;

import com.study.cheeper.model.User;

public class UserDto {

    private Integer id;
    private String name;
    private String bio;
    private String image;
    private String profileName;
    private String email;
    private int following;

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.bio = user.getBio();
        this.image = user.getImage();
        this.profileName = user.getProfileName();
        this.email = user.getEmail();
        this.following = user.getFollowing().size();
    }

    public Integer getId() { return id; }

    public String getName() { return name; }

    public String getBio() { return bio; }

    public String getImage() { return image; }

    public String getProfileName() { return profileName; }

    public String getFormattedProfileName() { return "@" + profileName; }

    public String getEmail() { return email; }

    public int getFollowing() { return following; }
}
