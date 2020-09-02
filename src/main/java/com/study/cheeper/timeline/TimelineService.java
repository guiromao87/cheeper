package com.study.cheeper.timeline;

import com.study.cheeper.cheep.CheepRepository;
import com.study.cheeper.login.LoggedUser;
import com.study.cheeper.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
class TimelineService {

    @Autowired
    private LoggedUser loggedUser;

    @Autowired
    private CheepRepository cheepRepository;

    public TimelineDto createTimeline(int page) {
        User current = loggedUser.asUser();

        Page<TimelineProjection> timelineProjections =
                cheepRepository.allCheepsWhomIFollow(current.getId(), PageRequest.of(page,5));

        return new TimelineDto(current, timelineProjections);
    }
}
