package com.study.cheeper.cheep;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class CheepDto {
    private long id;
    private LocalDateTime creation;
    private String message;
    private String profile;
    private String profileEmail;

    public CheepDto(Cheep cheep) {
        this.id = cheep.getId();
        this.creation = cheep.getCreation();
        this.message = cheep.getMessage();
        this.profile = cheep.getProfile().getName();
        this.profileEmail = cheep.getProfile().getEmail();
    }

    public long getId() { return id; }

    public LocalDateTime getCreation() { return creation; }

    public String getMessage() { return message; }

    public String getFormattedCreation() { return this.creation.format(DateTimeFormatter.ofPattern("dd-MM-yy HH:mm")); }

    public String getProfileEmail() { return profileEmail; }

    public String getProfile() {
        return profile;
    }

    public static List<CheepDto> toCheepsDto(List<Cheep> cheeps) {
        return cheeps.stream().map(CheepDto::new).collect(Collectors.toList());
    }
}
