package com.example.streeteatz.controller;

import com.example.streeteatz.services.EmailService;
import org.springframework.stereotype.Controller;

@Controller
public class EmailController {
    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }
}
