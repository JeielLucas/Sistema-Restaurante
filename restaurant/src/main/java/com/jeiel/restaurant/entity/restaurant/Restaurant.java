package com.jeiel.restaurant.entity.restaurant;

import com.jeiel.restaurant.entity.order.Order;
import com.jeiel.restaurant.entity.product.Product;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Restaurant {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private String address;
    private String phone;

    @OneToMany(mappedBy = "restaurant")
    private List<Product> products;

    @OneToMany(mappedBy = "restaurant")
    private List<Order> orders;

    private String rank;

    public Restaurant() {
    }

    public Restaurant(String id, String name, String address, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public Restaurant(String id, String name, String address, String phone, List<Product> products, List<Order> orders, String rank) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.products = products;
        this.orders = orders;
        this.rank = rank;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
