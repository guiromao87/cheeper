package com.study.cheeper.timeline;

import com.study.cheeper.cheep.Cheep;
import com.study.cheeper.cheep.CheepDto;
import com.study.cheeper.user.User;
import com.study.cheeper.user.UserDto;

import java.util.Collections;
import java.util.List;

class TimelineDto {

    private UserDto profile;
    private List<CheepDto> cheeps;

    public TimelineDto(User current, List<Cheep> allCheeps) {
        this.profile = new UserDto(current);
        this.cheeps = CheepDto.toCheepsDto(allCheeps);
    }

    public UserDto getProfile() { return profile; }

    public List<CheepDto> getCheeps() { return Collections.unmodifiableList(cheeps); }
}
