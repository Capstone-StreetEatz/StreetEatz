package com.example.streeteatz.controller;

import com.example.streeteatz.model.Truck;
import com.example.streeteatz.model.User;
import com.example.streeteatz.repository.TruckRepository;
import com.example.streeteatz.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TruckController {
    private UserRepository userDao;
    private TruckRepository truckDao;

    public TruckController(UserRepository userDao, TruckRepository truckDao) {
        this.userDao = userDao;
        this.truckDao = truckDao;
    }

    @GetMapping("/showAll")

    public String hello() {
        return "trucks/show_all";
    }

    @GetMapping("/truckInfo")
    public String showTruckInfoForm(Model model){
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.getUserById(loggedInUser.getId());
//        Truck loggedInOwner = (Truck) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Truck truck = new Truck();
        model.addAttribute("user", user);
        model.addAttribute("truck", truck);

        return "/trucks/truckInfo";
    }

    @PostMapping("/truckInfo")
    public String truckInfo(@ModelAttribute Truck truck){
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.getUserById(loggedInUser.getId());
        truck.setOwner(user);
        truck.setTruckName(user.getUsername());
        truckDao.save(truck);

        return "redirect:/profile";

    }
    @GetMapping("/editTruckInfo")
    public String editTruckInfoForm(Model model){
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.getUserById(loggedInUser.getId());
//        Truck loggedInOwner = (Truck) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Truck truck = truckDao.findByOwner(user);
        model.addAttribute("user", user);
        model.addAttribute("truck", truck);

        return "trucks/editTruckInfo";
    }

    @PostMapping("/editTruckInfo")
    public String editTruckInfo(@ModelAttribute Truck truck){
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.getUserById(loggedInUser.getId());
        truck.setOwner(user);
        truck.setTruckName(user.getUsername());
        truckDao.save(truck);

        return "redirect:/profile";

    }
}
