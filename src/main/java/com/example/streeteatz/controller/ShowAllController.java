package com.example.streeteatz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowAllController {

    @GetMapping("/showall")

    public String hello() {

        return "trucks/show_all";
    }



}
