package com.study.cheeper.timeline;

import com.study.cheeper.user.User;
import com.study.cheeper.user.UserDto;

import java.util.List;

class TimelineDto {

    private UserDto profile;
    private List<TimelineProjection> pageOfTimeline;

    public TimelineDto(User current, List<TimelineProjection> pageOfTimeline) {
        this.profile = new UserDto(current);
        this.pageOfTimeline = pageOfTimeline;
    }

    public UserDto getProfile() { return profile; }

    public List<TimelineProjection> getPageOfTimeline() {
        return pageOfTimeline;
    }
}
