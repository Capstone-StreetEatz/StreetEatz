package com.example.streeteatz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")


    public String hello(){
        return "redirect:/login";

    }

}
