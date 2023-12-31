package com.example.streeteatz.controller;

import com.example.streeteatz.model.Review;
import com.example.streeteatz.model.Truck;
import com.example.streeteatz.model.User;
import com.example.streeteatz.repository.ReviewRepository;
import com.example.streeteatz.repository.TruckRepository;
import com.example.streeteatz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TruckController {

    private UserRepository userDao;
    private TruckRepository truckDao;
    private ReviewRepository reviewsDao;

    public TruckController(UserRepository userDao, TruckRepository truckDao, ReviewRepository reviewsDao) {
        this.userDao = userDao;
        this.truckDao = truckDao;
        this.reviewsDao = reviewsDao;
    }

    @GetMapping("/showAll")
    public String showAllTrucks(Model model) {
        ArrayList<Truck> trucks = (ArrayList<Truck>) truckDao.findAll();
        model.addAttribute("trucks", trucks);
        return "trucks/show_all";
    }

    @GetMapping("/truckInfo")
    public String showTruckInfoForm(Model model) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.getUserById(loggedInUser.getId());
        Truck truck = new Truck();
        model.addAttribute("user", user);
        model.addAttribute("truck", truck);

        return "/trucks/truckInfo";
    }

    @PostMapping("/truckInfo")
    public String truckInfo(@ModelAttribute Truck truck) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.getUserById(loggedInUser.getId());
        truck.setOwner(user);
        user.setTruck(truck);
        truck.setTruckName(user.getUsername());
        truck.setAvatar(user.getAvatar());
        truckDao.save(truck);
        userDao.save(user);


        return "redirect:/profile";

    }
    @GetMapping("/editTruckInfo")
    public String editTruckInfoForm(Model model) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.getUserById(loggedInUser.getId());
        Truck truck = truckDao.findByOwner(user);

        model.addAttribute("user", user);
        model.addAttribute("truck", truck);

        return "trucks/editTruckInfo";
    }

    @PostMapping("/editTruckInfo")
    public String editTruckInfo(@ModelAttribute Truck truck) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.getUserById(loggedInUser.getId());

        user.setTruck(truck);
        truck.setOwner(user);

        truckDao.save(truck);

        return "redirect:/profile";

    }

    @GetMapping("/trucks/{id}")
    public String individualReview(@PathVariable int id, Model model) {

        Truck truck = truckDao.getTruckById(id);

        User owner = userDao.getUserById(truck.getOwner().getId());

        List<Review> truckReviews = reviewsDao.findAllByTruckId(id);

        model.addAttribute("truckReviews", truckReviews);


        model.addAttribute("truck", truck);
        model.addAttribute("user", owner);

        return "users/owner_profile";

    }
//    @GetMapping("/trucks/search/{truck_name}")
//    public String searchTrucks(@PathVariable String search, @PathVariable int id, Model model){
//        Truck truck = truckDao.getTruckById(id);
//        List<Truck> trucks = truckDao.findAllByTruckNameContainingIgnoreCaseAndIdOrderByIdAsc(search, id);
//        User owner = userDao.getUserById(truck.getOwner().getId());
//
//        model.addAttribute("trucks", trucks);
//        model.addAttribute("truck", truck);
//        model.addAttribute("user", owner);
//
//        return "trucks/show_all";
//    }
@GetMapping("/search")
public String searchTrucks(Model model ,@RequestParam String search) {
    ArrayList<Truck> truck = (ArrayList<Truck>) truckDao.findAllByTruckName(search);
    model.addAttribute("truck", truck);
    return "trucks/show_all";
}
}
