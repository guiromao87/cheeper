package com.study.cheeper.controller;

import com.study.cheeper.model.form.NewUserForm;
import com.study.cheeper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/registry")
    public String form(NewUserForm user) {
        return "/registry";
    }

    @PostMapping("/registry")
    public String registry(@Valid NewUserForm newUserForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors())
            return form(newUserForm);

        this.userService.createNewUser(newUserForm);
        redirectAttributes.addFlashAttribute("success", "Registro efetuado com sucesso");
        return "redirect:login";
    }
}
