package com.jeiel.restaurant.entity.user;

import com.jeiel.restaurant.entity.restaurant.Restaurant;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class RestaurantAccount {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String password;

    @ManyToMany
    @JoinTable(
            name = "restaurant_owners",
            joinColumns = @JoinColumn(name = "restaurant_account_id"),
            inverseJoinColumns = @JoinColumn(name = "restaurant_id")
    )
    private List<Restaurant> restaurants;

    public RestaurantAccount() {
    }

    public RestaurantAccount(String id, String name, String password, List<Restaurant> restaurants) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.restaurants = restaurants;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
}
