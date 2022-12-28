package com.json.example.product_shop.services.UserService;

import com.json.example.product_shop.domain.dtos.user.UserWithSoldProductsDTO;

import java.util.List;

public interface UserService {

    List<UserWithSoldProductsDTO> getUsersWithSoldProducts();
}
