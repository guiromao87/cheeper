package com.study.cheeper.controller;

import com.study.cheeper.model.User;
import com.study.cheeper.repository.CheepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @Autowired
    private CheepRepository cheepRepository;

    @GetMapping("/home")
    public ModelAndView home() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        ModelAndView mv = new ModelAndView("/home");
        mv.addObject("autor",user);
        mv.addObject("cheep", cheepRepository.findByAutorId(user.getId()));

        return mv;
    }

    @GetMapping("/")
    public ModelAndView defaultHome() { return home(); }

}
