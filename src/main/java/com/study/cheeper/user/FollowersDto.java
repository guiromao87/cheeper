package com.study.cheeper.user;

import java.util.Set;
import java.util.stream.Collectors;

public class FollowersDto {
    private String name;
    private String profileName;
    private String bio;

    public FollowersDto(User user) {
        this.name = user.getName();
        this.profileName = user.getProfileName();
        this.bio = user.getBio();
    }

    public String getName() {
        return name;
    }

    public String getProfileName() {
        return profileName;
    }

    public String getBio() {
        return bio;
    }

    public static Set<FollowersDto> toFollowersDto(Set<User> followers) {
        return followers.stream().map(FollowersDto::new).collect(Collectors.toSet());
    }
}
