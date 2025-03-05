package com.jeiel.restaurant.enums;

public enum UserRole {
    ADMIN("admin"),
    USER("user"),
    RESTAURANT_OWNER("restaurantOwner");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
