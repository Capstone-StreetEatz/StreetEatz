package com.example.streeteatz.repository;

import com.example.streeteatz.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    Review getReviewById(int id);
    Review deleteById(int id);


}
