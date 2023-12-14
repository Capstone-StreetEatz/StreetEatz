package com.example.streeteatz.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(length = 600)
    private String avatar;
    @Column(length = 500)
    private String bio;

    @OneToOne
    private Truck truck;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    List<Review> reviews;

    @Column
    private boolean truckOwner;

    public User() {
    }

    public User(int id, String username, String email, String password, String avatar, String bio, Truck truck, List<Review> reviews, boolean truckOwner) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
        this.bio = bio;
        this.truck = truck;
        this.reviews = reviews;
        this.truckOwner = truckOwner;
    }

    public User(String username, String email, String password, String avatar, List<Review> reviews, boolean truckOwner) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
        this.reviews = reviews;
        this.truckOwner = truckOwner;
    }

    public User(User copy) {
        id = copy.id;
        username = copy.username;
        email = copy.email;
        password = copy.password;
        avatar = copy.avatar;
        reviews = copy.reviews;
        truckOwner = copy.truckOwner;
    }


    public boolean isTruckOwner() {
        return truckOwner;
    }

    public void setTruckOwner(boolean truckOwner) {
        this.truckOwner = truckOwner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }
}