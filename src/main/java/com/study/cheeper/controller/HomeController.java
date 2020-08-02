package com.study.cheeper.controller;

import com.study.cheeper.model.User;
import com.study.cheeper.model.dto.CheepDto;
import com.study.cheeper.model.dto.UserDto;
import com.study.cheeper.repository.CheepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @Autowired
    private CheepRepository cheepRepository;

    @Autowired @Lazy
    private User loggedUser;

    @GetMapping(value = {"/", "/home"})
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("/home");
        mv.addObject("profile", new UserDto(loggedUser));
        mv.addObject("cheeps", CheepDto.toCheepsDto(cheepRepository.findByProfileId(loggedUser.getId())));

        return mv;
    }
}
