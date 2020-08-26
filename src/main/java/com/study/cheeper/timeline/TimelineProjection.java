package com.study.cheeper.timeline;

import java.time.LocalDateTime;

public interface TimelineProjection {
    String getName();
    String getEmail();
    Long getCheepId();
    String getMessage();
    LocalDateTime getCreation();
}
