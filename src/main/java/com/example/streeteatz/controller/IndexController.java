package com.example.streeteatz.controller;

import com.example.streeteatz.model.Truck;
import com.example.streeteatz.repository.TruckRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class IndexController {
    private TruckRepository truckDao;

    public IndexController(TruckRepository truckDao){
        this.truckDao = truckDao;
    }

    @GetMapping("/")
    public String hello(Model model){
        ArrayList<Truck> trucks = (ArrayList<Truck>) truckDao.findAll();

        model.addAttribute("trucks",trucks);
        return "reviews/index";
    }
    @GetMapping("/truckCarousel")
    public String showAllTrucks(Model model) {

        ArrayList<Truck> trucks = (ArrayList<Truck>) truckDao.findAll();

        model.addAttribute("trucks",trucks);
        return "trucks/show_all";
    }
}
