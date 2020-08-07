package com.study.cheeper.timeline;

import com.study.cheeper.cheep.Cheep;
import com.study.cheeper.cheep.CheepRepository;
import com.study.cheeper.login.LoggedUser;
import com.study.cheeper.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class TimelineService {

    @Autowired
    private LoggedUser loggedUser;

    @Autowired
    private CheepRepository cheepRepository;

    public TimelineDto createTimeline() {
        User current = loggedUser.asUser();
        return new TimelineDto(current, getAllCheeps(current));
    }

    private List<Cheep> getAllCheeps(User current) {
        List<Cheep> cheeps = new ArrayList<>();

        cheeps.addAll(cheepRepository.findByProfileId(current.getId()));
        current.getFollowing().forEach(f -> cheeps.addAll(cheepRepository.findByProfileId(f.getId())));
        cheeps.sort(Comparator.comparing(Cheep::getCreation).reversed());

        return cheeps;
    }
}
