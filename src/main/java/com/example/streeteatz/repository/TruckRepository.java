package com.example.streeteatz.repository;


import com.example.streeteatz.model.Truck;
import com.example.streeteatz.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;

import java.util.List;

public interface TruckRepository extends JpaRepository<Truck, Integer> {



    Truck getTruckById(int id);

    Truck findByOwner(User user);

    List<Truck> findAll();
    List<Truck> findAllByTruckName(String truckName);
}
