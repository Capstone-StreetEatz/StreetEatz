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

    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn
    private User owner;
    @Column(length = 300)
    private String website;

    @Column(nullable = false)
    private String location;

    @Column(length = 600)
    private String avatar;
    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Review> reviews;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="trucks_foodType",
            joinColumns={@JoinColumn(name="trucks_id")},
            inverseJoinColumns={@JoinColumn(name="foodType_id")}
    )
    private List<FoodType> foodType;

    public Truck(int id, String truckName, User owner, String website, String location, String avatar, List<Review> reviews, List<FoodType> foodType) {
        this.id = id;
        this.truckName = truckName;
        this.owner = owner;
        this.website = website;
        this.location = location;
        this.avatar = avatar;
        this.reviews = reviews;
        this.foodType = foodType;
    }

    public Truck(String truckName, User owner, String website, String location, String avatar, List<FoodType> foodType) {
        this.truckName = truckName;
        this.owner = owner;
        this.website = website;
        this.location = location;
        this.avatar = avatar;
        this.foodType = foodType;
    }

    public Truck() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<FoodType> getFoodType() {
        return foodType;
    }

    public void setFoodType(List<FoodType> foodType) {
        this.foodType = foodType;
    }

}