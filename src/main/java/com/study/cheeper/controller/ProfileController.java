package com.study.cheeper.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.study.cheeper.model.Cheep;
import com.study.cheeper.model.User;
import com.study.cheeper.model.dto.CheepDto;
import com.study.cheeper.model.dto.UserDto;
import com.study.cheeper.repository.CheepRepository;
import com.study.cheeper.repository.UserRepository;
import com.study.cheeper.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private CheepRepository cheepRepository;

    @Autowired
    private AmazonS3 amazonS3;

    @Autowired @Lazy
    private User loggedUser;


    @GetMapping("/{profileName}")
    public ModelAndView profile(@PathVariable("profileName") String profileName) {
        Optional<User> optional = this.userRepository.findByProfileName(profileName);

        if(!optional.isPresent())
            return new ModelAndView("/404");

        User user = optional.get();
        List<Cheep> cheepsByProfile = this.cheepRepository.findByProfileId(user.getId());

        ModelAndView mv = new ModelAndView("/profile");
        mv.addObject("profile", new UserDto(user));
        mv.addObject("cheeps", CheepDto.toCheepsDto(cheepsByProfile));
        mv.addObject("numberOfCheeps" , cheepsByProfile.size());
        return mv;
    }

    @GetMapping("/follow/{profileName}")
    public ModelAndView follow(@PathVariable String profileName) {
        return new ModelAndView("/follow.html");
    }

    @PostMapping("/upload")
    public ModelAndView upload(@RequestParam("image") MultipartFile image) throws IOException {
        if(image.getSize() > 0)
            profileService.uploadProfileImage(loggedUser.getId(), image);

        return new ModelAndView("redirect:/" + loggedUser.getProfileName());
    }

}
