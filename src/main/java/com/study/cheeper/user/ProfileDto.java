package com.study.cheeper.user;

import com.study.cheeper.cheep.Cheep;
import com.study.cheeper.cheep.CheepDto;
import org.springframework.data.domain.Page;

import java.util.List;

public class ProfileDto {
    private UserDto user;
    private List<CheepDto> cheeps;
    private long numberOfCheeps;
    private boolean follow;

    public ProfileDto(User profile, Page<Cheep> cheepPage) {
        this.user = new UserDto(profile);
        this.cheeps = CheepDto.toCheepsDto(cheepPage.getContent());
        this.numberOfCheeps = cheepPage.getTotalElements();
    }

    public void beingFollowed() { this.follow = true; }

    public UserDto getUser() { return user; }

    public List<CheepDto> getCheeps() { return cheeps; }

    public long getNumberOfCheeps() { return numberOfCheeps; }

    public boolean isFollow() { return follow; }
}