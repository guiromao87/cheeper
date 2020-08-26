package com.study.cheeper.cheep;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CheepDto {
    private String creation;
    private String message;

    public String getCreation() { return creation; }

    public void setCreation(LocalDateTime creation) { this.creation = creation.format(DateTimeFormatter.ofPattern("dd-MM-yy HH:mm")); }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    public static Page<CheepDto> toCheepDtoPage(Page<Cheep> cheepPage) {
        ModelMapper modelMapper = new ModelMapper();
        return cheepPage.map(cheep -> modelMapper.map(cheep, CheepDto.class));
    }
}
