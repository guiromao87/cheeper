package com.study.cheeper.controller;

import com.study.cheeper.model.User;
import com.study.cheeper.repository.UserRepository;
import com.study.cheeper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/registry")
    public String form() {
        return "/registry.html";
    }

    @PostMapping("/registry")
    public String registry(User user, RedirectAttributes redirectAttributes) {
        this.userService.save(user);
        redirectAttributes.addFlashAttribute("success", "Registro efetuado com sucesso");
        return "redirect:login";
    }
}
