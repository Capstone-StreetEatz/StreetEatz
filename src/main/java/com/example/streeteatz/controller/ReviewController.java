package com.example.streeteatz.controller;


import com.example.streeteatz.model.Review;
import com.example.streeteatz.model.Truck;
import com.example.streeteatz.model.User;
import com.example.streeteatz.repository.ReviewRepository;
import com.example.streeteatz.repository.TruckRepository;
import com.example.streeteatz.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class ReviewController {


    private final ReviewRepository reviewDao;
    private final UserRepository userDao;
    private final TruckRepository truckDao;


    public ReviewController(ReviewRepository reviewDao, UserRepository userDao, TruckRepository truckDao) {

        this.reviewDao = reviewDao;
        this.userDao = userDao;
        this.truckDao = truckDao;
    }




    @GetMapping("/reviews")
    public String indexPage(Model model){



        ArrayList<Review> reviews = (ArrayList<Review>) reviewDao.findAll();
        model.addAttribute("reviews", reviews);

        return "reviews/index";
    }

    @GetMapping("/reviews/{id}")
    public String individualReview(@PathVariable int id, Model model){

        Review review = reviewDao.getReviewById(id);


       model.addAttribute("review", review);

        return "reviews/show";
    }

    @GetMapping("/reviews/create/{id}")
    public String getCreate(@PathVariable int id, Model model){
    Truck truck = truckDao.getTruckById(id);

    model.addAttribute("truck", truck);
    model.addAttribute(new Review());

        return "reviews/create";
    }

    @PostMapping("/reviews/create/{id}")
    public String reviewCreate(@ModelAttribute ("review") Review review, @PathVariable int id){
        User loggedInUser = (User) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();

        User user = userDao.getUserById(loggedInUser.getId());
        Truck truck = truckDao.getTruckById(id);


        review.setUser(user);
        review.setTruck(truck);

        reviewDao.save(review);


        return "redirect:/profile";
    }

    @PostMapping("/reviews/delete")
    public String reviewDelete(@RequestParam int id, Model model){
        System.out.println(id);
        reviewDao.deleteById(id);

        return "redirect:/profile";
    }

    @GetMapping("/reviews/update/{id}")
    public String getUpdate(@PathVariable int id, Model model){

        Review review = reviewDao.getReviewById(id);

        model.addAttribute("review", review);

        return "reviews/update";
    }

    @PostMapping("/reviews/update")
    public String reviewUpdate(@ModelAttribute ("review") Review review){



        reviewDao.save(review);

        return "redirect:/profile";
    }
}
