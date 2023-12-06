package com.example.streeteatz.model;


import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "trucks")
public class Truck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String truckName;

    @OneToOne
    private User owner;

    @Column(nullable = false)
    private int location;

    @Column(length = 300)
    private String avatar;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="trucks_foodType",
            joinColumns={@JoinColumn(name="trucks_id")},
            inverseJoinColumns={@JoinColumn(name="foodType_id")}
    )
    private List<FoodType> foodType;

    public String getTruckName() {
        return truckName;
    }

    public void setTruckName(String truckName) {
        this.truckName = truckName;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<FoodType> getFoodType() {
        return foodType;
    }

    public void setFoodType(List<FoodType> foodType) {
        this.foodType = foodType;
    }
}