package com.study.cheeper.model.dto;

import com.study.cheeper.model.Cheep;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CheepDto {
    private long id;
    private LocalDateTime creation;
    private String message;

    public CheepDto(Cheep cheep) {
        this.id = cheep.getId();
        this.creation = cheep.getCreation();
        this.message = cheep.getMessage();
    }

    public long getId() { return id; }

    public LocalDateTime getCreation() { return creation; }

    public String getMessage() { return message; }

    public static List<CheepDto> toCheepsDto(List<Cheep> cheeps) {
        List<CheepDto> cheepDtos = new ArrayList<>();

        cheeps.forEach(cheep -> cheepDtos.add(new CheepDto(cheep)));

        return cheepDtos;
    }
}
