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
}