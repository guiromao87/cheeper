package com.study.cheeper.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TesteController {

    @GetMapping("cadastra")
    public String cadastra() { return "/cadastra.html"; }


    @GetMapping("/home")
    public String home() { return "/home.html"; }

}
