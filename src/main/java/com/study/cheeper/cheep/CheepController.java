package com.study.cheeper.cheep;

import com.study.cheeper.model.User;
import com.study.cheeper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    @Autowired @Lazy
    private User loggedUser;

    @PostMapping
    public String createNewCheep(NewCheepForm newCheepForm, RedirectAttributes attr) {
        User author = userRepository.getOne(loggedUser.getId());
        Cheep cheep = newCheepForm.toCheep(author);
        cheepRepository.save(cheep);

        attr.addFlashAttribute("success", "Cheep cadastrado com sucesso!");
        return "redirect:home";
    }

    @DeleteMapping
    public String delete(Long id) {
        Cheep toDelete = this.cheepRepository.getOne(id);

        if(toDelete.isOwnedBy(loggedUser))
            cheepRepository.delete(toDelete);

        return "redirect:/home";
    }

}
