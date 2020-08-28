package com.study.cheeper.user;

import com.study.cheeper.cheep.Cheep;
import com.study.cheeper.cheep.CheepDto;
import org.springframework.data.domain.Page;

public class ProfileDto {
    private UserDto user;
    private Page<CheepDto> cheepPage;
    private long numberOfCheeps;
    private boolean follow;

    public ProfileDto(User profile, Page<Cheep> cheepPage, int numberOfIfollow, int numberOfFollowsMe) {
        this.user = new UserDto(profile, numberOfIfollow, numberOfFollowsMe);
        this.cheepPage = CheepDto.toCheepDtoPage(cheepPage);
        this.numberOfCheeps = cheepPage.getTotalElements();
    }

    public void beingFollowed() { this.follow = true; }

    public UserDto getUser() { return user; }

    public Page<CheepDto> getCheepPage() { return cheepPage; }

    public long getNumberOfCheeps() { return numberOfCheeps; }

    public boolean isFollow() { return follow; }
}