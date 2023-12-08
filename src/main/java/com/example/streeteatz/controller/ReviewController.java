package com.example.streeteatz.controller;


import com.example.streeteatz.model.Review;

import com.example.streeteatz.model.User;
import com.example.streeteatz.repository.ReviewRepository;
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


    public ReviewController(ReviewRepository reviewDao, UserRepository userDao) {

        this.reviewDao = reviewDao;
        this.userDao = userDao;
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

    @GetMapping("/reviews/create")
    public String getCreate(Model model){
    model.addAttribute(new Review());


        return "/reviews/create";
    }

    @PostMapping("/reviews/create")
    public String reviewCreate(@ModelAttribute ("review") Review review){
        User loggedInUser = (User) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();

        User user = userDao.getUserById(loggedInUser.getId());


        review.setUser(user);

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
