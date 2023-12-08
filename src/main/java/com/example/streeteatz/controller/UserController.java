package com.example.streeteatz.controller;


import com.example.streeteatz.model.Review;
import com.example.streeteatz.model.User;
import com.example.streeteatz.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    private UserRepository userDao;
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder){
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/sign-up")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "users/sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        System.out.println(user.isTruckOwner());
        userDao.save(user);
        return "redirect:/login";
    }


    @GetMapping("/index")
    public String showLogIn(Model model){
        model.addAttribute("user", new User());
        return "reviews/index";
    }
    @GetMapping("/profile")
    public String showProfile(Model model, Principal principal) {
        User user = userDao.findByUsername(principal.getName());

        List<Review> reviews = user.getReviews();

        model.addAttribute("user", user);
        model.addAttribute("reviews", reviews);

        user.setTruckOwner(user.isTruckOwner());

        if (user.isTruckOwner()) {
            return "users/owner_profile";
        } else {
            return "users/user_profile";
        }
    }

}

