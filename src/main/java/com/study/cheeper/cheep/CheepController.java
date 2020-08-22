package com.study.cheeper.cheep;

import com.study.cheeper.login.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cheep")
public class CheepController {

    @Autowired
    private CheepService cheepService;

    @Autowired
    private LoggedUser loggedUser;

    @PostMapping
    public String createNewCheep(NewCheepForm newCheepForm, RedirectAttributes attr) {
        Cheep cheep = newCheepForm.toCheep(loggedUser.asUser());
        this.cheepService.create(cheep);

        attr.addFlashAttribute("success", "Cheep cadastrado com sucesso!");
        return "redirect:home";
    }

    @DeleteMapping
    public String delete(Long id) {
        this.cheepService.delete(id);
        return "redirect:home";
    }

}
