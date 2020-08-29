package com.study.cheeper.timeline;

import com.study.cheeper.user.User;
import com.study.cheeper.user.UserDto;
import org.springframework.data.domain.Page;

class TimelineDto {

    private UserDto profile;
    private Page<TimelineProjection> pageOfTimeline;

    public TimelineDto(User current, Page<TimelineProjection> pageOfTimeline) {
        this.profile = new UserDto(current);
        this.pageOfTimeline = pageOfTimeline;
    }

    public UserDto getProfile() { return profile; }

    public Page<TimelineProjection> getPageOfTimeline() { return pageOfTimeline; }
}
