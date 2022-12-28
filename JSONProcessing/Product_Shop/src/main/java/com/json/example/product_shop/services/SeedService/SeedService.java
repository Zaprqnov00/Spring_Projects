package com.json.example.product_shop.services.SeedService;

import java.io.FileNotFoundException;

public interface SeedService {

    void seedUsers() throws FileNotFoundException;

    void seedProducts() throws FileNotFoundException;

    void seedCategories() throws FileNotFoundException;

    default void seedAllData() throws FileNotFoundException {
        seedUsers();
        seedProducts();
        seedCategories();
    }

}
