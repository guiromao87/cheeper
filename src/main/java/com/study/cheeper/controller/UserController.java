package com.study.cheeper.controller;

import com.study.cheeper.model.User;
import com.study.cheeper.repository.UserRepository;
import com.study.cheeper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/registry")
    public String form(User user) {
        return "/registry";
    }

    @PostMapping("/registry")
    public String registry(@Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            return form(user);
        }
        this.userService.save(user);
        redirectAttributes.addFlashAttribute("success", "Registro efetuado com sucesso");
        return "redirect:login";
    }
}
