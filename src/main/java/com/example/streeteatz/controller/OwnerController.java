//package com.example.streeteatz.controller;
//
//import com.example.streeteatz.model.User;
//import com.example.streeteatz.repository.UserRepository;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//public class OwnerController {
//    private UserRepository userDao;
//    private PasswordEncoder passwordEncoder;
//    public OwnerController(UserRepository userDao, PasswordEncoder passwordEncoder) {
//        this.userDao = userDao;
//        this.passwordEncoder = passwordEncoder;
//    }
//    @PostMapping("/owners/register")
//    public String registerOwner(@ModelAttribute User owner, @RequestParam String role) {
//        // ... your existing code for owner registration
//
//        if (role.equals("ROLE_OWNER")) {
//            return "redirect:/owners/profile";
//        } else {
//            return "redirect:/users/profile";
//        }
//    }
//}
