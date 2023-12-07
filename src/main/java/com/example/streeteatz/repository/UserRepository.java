package com.example.streeteatz.repository;


import com.example.streeteatz.model.Review;
import com.example.streeteatz.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    User getUserById(int id);
    User findByUsername(String username);




}
