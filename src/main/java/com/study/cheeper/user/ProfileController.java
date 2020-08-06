package com.study.cheeper.user;

import com.study.cheeper.cheep.Cheep;
import com.study.cheeper.cheep.CheepDto;
import com.study.cheeper.cheep.CheepRepository;
import com.study.cheeper.email.VerifyEmailForm;
import com.study.cheeper.email.VerifyEmailService;
import com.study.cheeper.login.LoggedUser;
import com.study.cheeper.login.UserSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    private LoggedUser loggedUser;

    @Autowired
    private VerifyEmailService verifyEmailService;

    @GetMapping("/{profileName}")
    public ModelAndView profile(@PathVariable("profileName") String profileName) {
        Optional<User> optional = this.userRepository.findByProfileName(profileName);

        if(!optional.isPresent())
            return new ModelAndView("404");

        User profile = optional.get();
        User current = loggedUser.asUser();
        boolean isFollowed = false;

        if(profile.isNotTheSameAs(current) && (current.isFollowing(profile)))
            isFollowed = true;

        ModelAndView mv = new ModelAndView("profile");

        List<Cheep> cheepsByProfile = this.cheepRepository.findByProfileId(profile.getId());
        mv.addObject("profile", new UserDto(profile));
        mv.addObject("isFollowed", isFollowed);
        mv.addObject("cheeps", CheepDto.toCheepsDto(cheepsByProfile));
        mv.addObject("numberOfCheeps" , cheepsByProfile.size());
        return mv;
    }

    @ResponseBody
    @PostMapping(value = {"/follow", "/unfollow"})
    public void followOrUnfollow(@RequestBody String profileName) {
        User follower = loggedUser.asUser();
        Optional<User> optionalToBeFollowed = userRepository.findByProfileName(profileName);

        if(optionalToBeFollowed.isPresent()) {

            if(follower.getFollowing().contains(optionalToBeFollowed.get()))
                follower.unfollow(optionalToBeFollowed.get());
            else
                follower.follow(optionalToBeFollowed.get());

            User followerSaved = userRepository.save(follower);

            UserSummary userSummary = followerSaved.toUserSummary();
            final Authentication auth = new UsernamePasswordAuthenticationToken(userSummary, null, null);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
    }

    @PostMapping("/upload")
    public ModelAndView upload(@RequestParam("image") MultipartFile image) throws IOException {
        if(image.getSize() > 0)
            profileService.uploadProfileImage(loggedUser.getId(), image);

        return new ModelAndView("redirect:/" + loggedUser.getProfileName());
    }

    @GetMapping("/form-verify")
    public ModelAndView formVerify(VerifyEmailForm verifyEmailForm) {
        ModelAndView mv = new ModelAndView("confirm-email");
        mv.addObject("email", loggedUser.getEmail());
        return mv;
    }

    @PostMapping("/verify")
    public ModelAndView verify(VerifyEmailForm verifyEmailForm) {
        boolean isCodeCorrect = this.verifyEmailService.verify(loggedUser.getEmail(), verifyEmailForm.getCode());
        if(isCodeCorrect) {
            User user = loggedUser.asUser();
            user.setVerifiedEmail(true);
            this.userRepository.save(user);
            this.verifyEmailService.remove(user.getEmail());

            UserSummary userSummary = user.toUserSummary();
            final Authentication auth = new UsernamePasswordAuthenticationToken(userSummary, null, null);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        return new ModelAndView("redirect:/" + loggedUser.getProfileName());
    }
}