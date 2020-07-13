package com.study.cheeper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TesteController {

    @GetMapping("/home")
    public String home() { return "/home.html"; }

    @GetMapping("/")
    public String home2() { return "/home.html"; }

}
