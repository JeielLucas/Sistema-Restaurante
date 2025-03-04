package com.jeiel.restaurant.entity.order;

import com.jeiel.restaurant.entity.restaurant.Restaurant;
import com.jeiel.restaurant.entity.user.User;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "restaurant_id") // FK do restaurante
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "user_id") // FK do cliente
    private User user;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> items;

    public Order() {
    }

    public Order(String id, Restaurant restaurant, User user, List<OrderItem> items) {
        this.id = id;
        this.restaurant = restaurant;
        this.user = user;
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}
