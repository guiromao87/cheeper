package com.study.cheeper.controller;

import org.slf4j.Logger;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "/home";
    }

    @GetMapping("/")
    public String home2() { return "/home"; }

}
