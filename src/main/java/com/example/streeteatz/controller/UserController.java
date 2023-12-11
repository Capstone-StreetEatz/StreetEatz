package com.example.streeteatz.controller;



import com.example.streeteatz.model.Truck;

import com.example.streeteatz.model.Review;

import com.example.streeteatz.model.User;
import com.example.streeteatz.repository.TruckRepository;
import com.example.streeteatz.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    private UserRepository userDao;
    private TruckRepository truckDao;
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder, TruckRepository truckDao) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.truckDao= truckDao;
    }

    @GetMapping("/sign-up")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "users/sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user) {
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(user);
        return "redirect:/login";
    }


    @GetMapping("/index")
    public String showLogIn(Model model) {
        model.addAttribute("user", new User());
        return "reviews/index";
    }
    //To show ALL users the user profile page:
    @GetMapping("/profile")
    public String showProfile(Model model) {
      
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.getUserById(loggedInUser.getId());
        Truck truck = truckDao.findByOwner(user);
        List<Review> reviews = user.getReviews();

        model.addAttribute("user", user);
        model.addAttribute("truck", truck);
        model.addAttribute("isTruckOwner", user.isTruckOwner());
        model.addAttribute("reviews", reviews);

//        user.setTruckOwner(user.isTruckOwner());

        if (user.isTruckOwner()){
            return "users/owner_profile";
        }else {
            return "users/user_profile";
        }
    }

    @GetMapping("/updateUser")
    public String showUpdateUserForm(Model model) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.getUserById(loggedInUser.getId());
        model.addAttribute("user", user);
        return "users/updateProfile";
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(user);
        return "redirect:/profile";
    }
    @GetMapping("/updateOwner")
    public String showUpdateOwnerForm(Model model) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.getUserById(loggedInUser.getId());
        model.addAttribute("user", user);
        return "users/updateOwner";
    }

    @PostMapping("/updateOwner")
    public String updateOwner(@ModelAttribute User user){
//        System.out.println(user.isTruckOwner());
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(user);
        return "redirect:/profile";
    }
    //To show SOME USERS a truck profile page

//    @GetMapping("/myTruckProfile")
//    public String showUserProfile(Model model, Principal principal) {
//        User user = userDao.findByUsername(principal.getName());
//        model.addAttribute("user", user);
//
//        user.setTruckOwner(user.isTruckOwner());
//
//        return "users/owner_profile";
//
//    }

}

