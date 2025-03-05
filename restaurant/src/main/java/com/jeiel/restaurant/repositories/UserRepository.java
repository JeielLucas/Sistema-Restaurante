package com.jeiel.restaurant.repositories;

import com.jeiel.restaurant.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    User findUserByEmail(String email);
}
