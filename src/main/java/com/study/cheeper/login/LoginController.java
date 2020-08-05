package com.study.cheeper.login;

import com.study.cheeper.user.NewUserForm;
import com.study.cheeper.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class LoginController {

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.addValidators(new ConfirmPasswordValidator());
    }

    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginService;


    @GetMapping("/login")
    public String loginForm() { return "/login"; }

    @GetMapping("/registry")
    public String form(NewUserForm user) {
        return "/registry";
    }

    @PostMapping("/registry")
    public String registry(@Valid NewUserForm newUserForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors())
            return form(newUserForm);

        this.userService.createNewUser(newUserForm);
        this.loginService.sendEmailTo(newUserForm.getEmail());

        redirectAttributes.addFlashAttribute("success", "Registro efetuado com sucesso");
        return "redirect:login";
    }
}
