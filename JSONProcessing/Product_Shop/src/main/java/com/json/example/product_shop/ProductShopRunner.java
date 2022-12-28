package com.json.example.product_shop;

import com.json.example.product_shop.domain.dtos.category.CategoryStats;
import com.json.example.product_shop.domain.dtos.product.GetAllProductsInformation;
import com.json.example.product_shop.domain.dtos.user.UserWithSoldProductsDTO;
import com.json.example.product_shop.services.ProductService.ProductService;
import com.json.example.product_shop.services.SeedService.SeedService;
import com.json.example.product_shop.services.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

import static com.json.example.product_shop.constants.Utils.GSON;

@Component
public class ProductShopRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public ProductShopRunner(SeedService seedService, UserService userService, ProductService productService) {
        this.seedService = seedService;
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {
        //Seed allData -------->
        //this.seedService.seedAllData();

        //Queries ------->
        //getUsersWithSoldProducts();
        //getCategoryStatistics();
        List<GetAllProductsInformation> productsInRange = this.productService.getProductsInRange();

        String json = GSON.toJson(productsInRange);

        System.out.println(json);
    }

    private void getCategoryStatistics() {
        List<CategoryStats> categoryStatistics = this.productService.getCategoryStatistics();

        String json = GSON.toJson(categoryStatistics);

        System.out.println(json);
    }

    private void getUsersWithSoldProducts() {
        List<UserWithSoldProductsDTO> usersWithSoldProducts = this.userService.getUsersWithSoldProducts();

        String json = GSON.toJson(usersWithSoldProducts);

        System.out.println(json);
    }
}
