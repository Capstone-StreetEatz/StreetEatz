package com.example.streeteatz.repository;


import com.example.streeteatz.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User getUserById(int id);
    User findByUsername(String username);

}
