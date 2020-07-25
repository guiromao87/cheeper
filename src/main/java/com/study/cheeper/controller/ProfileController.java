package com.study.cheeper.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.study.cheeper.model.User;
import com.study.cheeper.model.dto.UserDto;
import com.study.cheeper.repository.CheepRepository;
import com.study.cheeper.repository.UserRepository;
import com.study.cheeper.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
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

    @GetMapping("/profile/{id}")
    public ModelAndView profile(@PathVariable("id") int id) {
        Optional<User> optional = this.userRepository.findById(id);

        if(!optional.isPresent())
            return new ModelAndView("/404");

        ModelAndView mv = new ModelAndView("/profile");
        mv.addObject("user", new UserDto(optional.get()));
        mv.addObject("numberOfCheeps" , this.cheepRepository.countByAutorId(id));
        return mv;
    }

    @PostMapping("/profile/upload")
    public ModelAndView upload(Integer id, @RequestParam("image") MultipartFile image) throws IOException {
        if(image.getSize() > 0)
            profileService.uploadProfileImage(id, image);

        return profile(id);
    }

}
