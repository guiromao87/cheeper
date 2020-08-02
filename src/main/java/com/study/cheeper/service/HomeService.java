package com.study.cheeper.service;

import com.study.cheeper.model.Cheep;
import com.study.cheeper.model.User;
import com.study.cheeper.repository.CheepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class HomeService {

    @Autowired @Lazy
    private User loggedUser;

    @Autowired
    private CheepRepository cheepRepository;

    public List<Cheep> createTimeline() {
        List<Cheep> timelineCheeps = new ArrayList<>();
        timelineCheeps.addAll(cheepRepository.findByProfileId(loggedUser.getId()));
        loggedUser.getFollowing().forEach(f -> timelineCheeps.addAll(cheepRepository.findByProfileId(f.getId())));
        timelineCheeps.sort(Comparator.comparing(Cheep::getCreation));

        return timelineCheeps;
    }
}
