package com.study.cheeper.user;

import com.study.cheeper.cheep.Cheep;
import com.study.cheeper.cheep.CheepDto;

import java.util.List;

public class ProfileDto {
    private UserDto user;
    private List<CheepDto> cheeps;
    private int numberOfCheeps;
    private boolean follow;

    public ProfileDto(User profile, List<Cheep> cheepsByProfile) {
        this.user = new UserDto(profile);
        this.cheeps = CheepDto.toCheepsDto(cheepsByProfile);
        this.numberOfCheeps = cheepsByProfile.size();
    }

    public void beingFollowed() { this.follow = true; }

    public UserDto getUser() { return user; }

    public List<CheepDto> getCheeps() { return cheeps; }

    public int getNumberOfCheeps() { return numberOfCheeps; }

    public boolean isFollow() { return follow; }
}