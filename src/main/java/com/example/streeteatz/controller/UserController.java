package com.example.streeteatz.controller;



import com.example.streeteatz.model.Truck;
import com.example.streeteatz.model.Review;
import com.example.streeteatz.model.User;
import com.example.streeteatz.repository.ReviewRepository;
import com.example.streeteatz.repository.TruckRepository;
import com.example.streeteatz.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private UserRepository userDao;
    private TruckRepository truckDao;
    private ReviewRepository reviewsDao;

    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder, TruckRepository truckDao, ReviewRepository reviewsDao) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.truckDao= truckDao;
        this.reviewsDao = reviewsDao;
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
        ArrayList<Truck> trucks = (ArrayList<Truck>) truckDao.findAll();
        model.addAttribute("trucks",trucks);
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
        user.setTruck(truck);


        model.addAttribute("user", user);
        model.addAttribute("truck", truck);
        model.addAttribute("isTruckOwner", user.isTruckOwner());
        model.addAttribute("reviews", reviews);


//        user.setTruckOwner(user.isTruckOwner());

        if (user.isTruckOwner()){
//            List<Review> truckReviews = reviewsDao.findAllByTruckId(truck.getId());
//            model.addAttribute("truckReviews", truckReviews);
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

    @PostMapping("/imgUpload")
    public String imgUpload(@RequestParam(name = "img") String imgLink){
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.getUserById(loggedInUser.getId());
        Truck truck = truckDao.findByOwner(user);
        user.setAvatar(imgLink);
        truck.setAvatar(user.getAvatar());
        userDao.save(user);

        return "redirect:/profile";
    }

    @GetMapping("/deleteUser")
    public String showDeleteUserForm(Model model) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.getUserById(loggedInUser.getId());
        model.addAttribute("user", user);
        return "users/deleteConfirmation";
    }
    @PostMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Integer id){
        Truck truck= truckDao.findByOwner(userDao.getUserById(id));
        truckDao.deleteById(truck.getId());
        userDao.deleteById(id);
        return "redirect:/index";
    }
}

