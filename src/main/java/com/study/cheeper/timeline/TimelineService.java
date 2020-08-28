package com.study.cheeper.timeline;

import com.study.cheeper.cheep.CheepRepository;
import com.study.cheeper.login.LoggedUser;
import com.study.cheeper.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class TimelineService {

    @Autowired
    private LoggedUser loggedUser;

    @Autowired
    private CheepRepository cheepRepository;

    public TimelineDto createTimeline() {
        User current = loggedUser.asUser();

        List<TimelineProjection> timelineProjections = cheepRepository.allCheepsWhomIFollow(current.getId());
        return new TimelineDto(current, timelineProjections);
    }
}
