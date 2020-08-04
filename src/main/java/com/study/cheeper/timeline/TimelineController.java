package com.study.cheeper.timeline;

import com.study.cheeper.cheep.CheepDto;
import com.study.cheeper.cheep.CheepRepository;
import com.study.cheeper.user.User;
import com.study.cheeper.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TimelineController {

    @Autowired
    private CheepRepository cheepRepository;

    @Autowired @Lazy
    private User loggedUser;

    @Autowired
    private TimelineService homeService;

    @GetMapping(value = {"/", "/home"})
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("/home");
        mv.addObject("profile", new UserDto(loggedUser));
        mv.addObject("cheeps", CheepDto.toCheepsDto(homeService.createTimeline()));

        return mv;
    }
}
