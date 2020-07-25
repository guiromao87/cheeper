package com.study.cheeper.controller;

import com.study.cheeper.model.Cheep;
import com.study.cheeper.model.User;
import com.study.cheeper.model.dto.CheepDto;
import com.study.cheeper.model.dto.UserDto;
import com.study.cheeper.repository.CheepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private CheepRepository cheepRepository;

    @GetMapping(value = {"/", "/home"})
    public ModelAndView home() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Cheep> cheepsByAutor = cheepRepository.findByAutorId(user.getId());

        ModelAndView mv = new ModelAndView("/home");
        mv.addObject("autor", new UserDto(user));
        mv.addObject("cheeps", CheepDto.toCheepsDto(cheepsByAutor));

        return mv;
    }
}
