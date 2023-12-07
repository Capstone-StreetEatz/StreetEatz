package com.example.streeteatz.repository;


import com.example.streeteatz.model.Truck;
import com.example.streeteatz.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TruckRepository extends JpaRepository<User, Integer> {

    Truck getById (int Id);
    User findByUsername(String username);

}
