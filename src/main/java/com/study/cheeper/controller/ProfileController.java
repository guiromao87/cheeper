package com.study.cheeper.controller;

import com.study.cheeper.model.User;
import com.study.cheeper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/profile/{id}")
    public ModelAndView profile(@PathVariable("id") int id) {
        Optional<User> optional = this.userRepository.findById(id);

        if(!optional.isPresent())
            return new ModelAndView("/404");

        ModelAndView mv = new ModelAndView("/profile");
        mv.addObject("user", optional.get());
        return mv;
    }

    @PostMapping("/profile/edit")
    public String edit(Integer id) {
        return "/edit";
    }

}
