package com.example.streeteatz.model;

import jakarta.persistence.*;

@Entity
@Table(name="reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(nullable = false, length = 500)
    private String body;

    @Column( length = 300)
    private String picture;

    @Column(nullable = false, length = 2)
    private int rating;

    @ManyToOne
    @JoinColumn (name = "user")
    private User user;

    @ManyToOne
    @JoinColumn (name = "truck_id")
    private Truck truck;

    public Review() {
    }

    public Review(int id, String title, String body, String picture, int rating, User user, Truck truck) {
        this.id = id;
        this.body = body;
        this.picture = picture;
        this.rating = rating;
        this.user = user;
        this.truck = truck;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }
}


