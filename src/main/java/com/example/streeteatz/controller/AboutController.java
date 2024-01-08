package com.example.streeteatz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {
    @GetMapping("/about")
    public String hello() {
        return "reviews/about";
    }
    @GetMapping("/aboutTeam")
    public String aboutTeam() {
        return "reviews/aboutTeam";
    }
}
