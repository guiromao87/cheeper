package com.study.cheeper.timeline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TimelineController {

    @Autowired
    private TimelineService timelineService;

    @GetMapping(value = {"/", "/home"})
    public ModelAndView timeline() {
        ModelAndView mv = new ModelAndView("timeline");

        mv.addObject("timeline", timelineService.createTimeline());
        return mv;
    }
}
