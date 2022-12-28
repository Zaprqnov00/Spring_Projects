package com.json.example.product_shop.repositories;

import com.json.example.product_shop.domain.dtos.user.UserWithSoldProductsDTO;
import com.json.example.product_shop.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u" +
            " JOIN u.sellProducts p" +
            " WHERE p.buyer IS NOT NULL")
    List<User> findAllWithSoldProducts();
}
