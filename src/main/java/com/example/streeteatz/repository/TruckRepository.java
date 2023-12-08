package com.example.streeteatz.repository;


import com.example.streeteatz.model.Truck;
import com.example.streeteatz.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TruckRepository extends JpaRepository<Truck, Integer> {



//    Truck getUserById(int id);
//    Truck findByUsername(String username);

    Truck findByOwner(User user);


}
