package com.study.cheeper.timeline;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.cheeper.cheep.Cheep;
import com.study.cheeper.cheep.CheepRepository;
import com.study.cheeper.login.LoggedUser;
import com.study.cheeper.user.User;

@Service
public class TimelineService {

    @Autowired
    private LoggedUser loggedUser;

    @Autowired
    private CheepRepository cheepRepository;

    public List<Cheep> createTimeline() {
        List<Cheep> timelineCheeps = new ArrayList<>();
        User current = loggedUser.asUser();
        timelineCheeps.addAll(cheepRepository.findByProfileId(current.getId()));
        current.getFollowing().forEach(f -> timelineCheeps.addAll(cheepRepository.findByProfileId(f.getId())));
        timelineCheeps.sort(Comparator.comparing(Cheep::getCreation));

        return timelineCheeps;
    }
}
