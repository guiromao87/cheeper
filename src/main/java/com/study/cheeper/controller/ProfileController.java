package com.study.cheeper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfileController {

    @GetMapping("/profile/{id}")
    public ModelAndView profile(@PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView("/profile");
        return mv;
    }

}
