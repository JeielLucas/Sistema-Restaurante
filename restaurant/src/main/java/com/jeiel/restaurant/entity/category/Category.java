package com.jeiel.restaurant.entity.category;

import com.jeiel.restaurant.entity.product.Product;
import com.jeiel.restaurant.entity.restaurant.Restaurant;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "restaurant_id") // Cria FK na tabela para relacionar ao restaurante
    private Restaurant restaurant;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public Category() {
    }

    public Category(String id, String name, Restaurant restaurant) {
        this.id = id;
        this.name = name;
        this.restaurant = restaurant;
    }

    public Category(String id, String name, Restaurant restaurant, List<Product> products) {
        this.id = id;
        this.name = name;
        this.restaurant = restaurant;
        this.products = products;
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

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
