package com.jeiel.restaurant.entity.product;

import com.jeiel.restaurant.entity.category.Category;
import com.jeiel.restaurant.entity.restaurant.Restaurant;
import jakarta.persistence.*;

@Entity
@Table
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String description;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "restaurant_id") // Relaciona cada produto a um restaurante
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = true) // Relaciona cada produto a uma categoria
    private Category category;

    public Product() {
    }

    public Product(String id, String name, String description, Double price, Restaurant restaurant) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.restaurant = restaurant;
    }

    public Product(String id, String name, String description, Double price, Restaurant restaurant, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.restaurant = restaurant;
        this.category = category;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
