package com.example.streeteatz.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TypeOfFood")
public class FoodType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String FoodType;

    public FoodType() {
    }

    public FoodType(Long id, String foodType) {
        this.id = id;
        FoodType = foodType;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getFoodType() {
        return FoodType;
    }

    public void setFoodType(String foodType) {
        FoodType = foodType;
    }
}
