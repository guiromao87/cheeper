package com.study.cheeper.model.dto;

import com.study.cheeper.model.User;

public class UserDto {

    private Integer id;
    private String name;
    private String bio;
    private String image;

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.bio = user.getBio();
        this.image = user.getImage();
    }

    public Integer getId() { return id; }

    public String getName() { return name; }

    public String getBio() { return bio; }

    public String getImage() { return image; }
}