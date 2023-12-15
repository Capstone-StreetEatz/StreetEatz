package com.example.streeteatz.repository;

import com.example.streeteatz.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    Review getReviewById(int id);
    Review deleteById(int id);
    List<Review> findAllByUserId(int user_id);
    List<Review> findAllByTruckId(int truck_id);
    List<Review> deleteAllByTruckId(int truck_id);

}
