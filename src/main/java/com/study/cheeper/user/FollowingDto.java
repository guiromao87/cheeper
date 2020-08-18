package com.study.cheeper.user;

import java.util.Set;
import java.util.stream.Collectors;

public class FollowingDto {
    private String name;
    private String profileName;
    private String bio;

    public FollowingDto(User user) {
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

    public static Set<FollowingDto> toFollowingsDto(Set<User> followings) {
        return followings.stream().map(FollowingDto::new).collect(Collectors.toSet());
    }
}
