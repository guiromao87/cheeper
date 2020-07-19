package com.study.cheeper.controller;

import com.study.cheeper.model.Cheep;
import com.study.cheeper.model.form.NewCheepForm;
import com.study.cheeper.repository.CheepRepository;
import com.study.cheeper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cheep")
public class CheepController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CheepRepository cheepRepository;

    @PostMapping
    public String createNewCheep(NewCheepForm newCheepForm, RedirectAttributes attr) {
        Cheep cheep = newCheepForm.toCheep(userRepository);
        cheepRepository.save(cheep);

        attr.addFlashAttribute("success", "Cheep cadastrado com sucesso!");

        return "redirect:home";
    }

}
