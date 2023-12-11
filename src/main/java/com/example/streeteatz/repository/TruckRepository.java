package com.example.streeteatz.repository;


import com.example.streeteatz.model.Truck;
import com.example.streeteatz.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TruckRepository extends JpaRepository<Truck, Integer> {



    Truck getTruckById(int id);
//    Truck findTruckBy(String username);

    Truck findByOwner(User user);

//    Truck findTruckByOwner(int id);

    List<Truck> findAll();
}
