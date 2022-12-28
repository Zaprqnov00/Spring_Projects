package com.json.example.product_shop.services.SeedService;

import com.json.example.product_shop.domain.dtos.category.CategoryImportDTO;
import com.json.example.product_shop.domain.dtos.product.ProductImportDTO;
import com.json.example.product_shop.domain.dtos.user.UserImportDTO;
import com.json.example.product_shop.domain.entities.Category;
import com.json.example.product_shop.domain.entities.Product;
import com.json.example.product_shop.domain.entities.User;
import com.json.example.product_shop.repositories.CategoryRepository;
import com.json.example.product_shop.repositories.ProductRepository;
import com.json.example.product_shop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static com.json.example.product_shop.constants.Paths.*;
import static com.json.example.product_shop.constants.Utils.GSON;
import static com.json.example.product_shop.constants.Utils.MODEL_MAPPER;

@Service
public class SeedServiceImpl implements SeedService {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    public SeedServiceImpl(UserRepository userRepository,
                           CategoryRepository categoryRepository,
                           ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void seedUsers() throws FileNotFoundException {
        FileReader fileReader = new FileReader(USER_JSON_PATH.toFile());
        UserImportDTO[] userImportDTOS = GSON.fromJson(fileReader, UserImportDTO[].class);

        List<User> users = Arrays
                .stream(userImportDTOS)
                .map(userMap -> MODEL_MAPPER.map(userMap, User.class))
                .collect(Collectors.toList());

        this.userRepository.saveAll(users);
    }


    @Override
    public void seedCategories() throws FileNotFoundException {
        FileReader fileReader = new FileReader(CATEGORY_JSON_PATH.toFile());
        CategoryImportDTO[] categoryImportDTOS = GSON.fromJson(fileReader, CategoryImportDTO[].class);

        List<Category> categories = Arrays
                .stream(categoryImportDTOS)
                .map(categoryMap -> MODEL_MAPPER.map(categoryMap, Category.class))
                .collect(Collectors.toList());

        this.categoryRepository.saveAll(categories);
    }

    @Override
    public void seedProducts() throws FileNotFoundException {
        FileReader fileReader = new FileReader(PRODUCT_JSON_PATH.toFile());
        ProductImportDTO[] productImportDTOS = GSON.fromJson(fileReader, ProductImportDTO[].class);

        List<Product> products = Arrays
                .stream(productImportDTOS)
                .map(productMap -> MODEL_MAPPER.map(productMap, Product.class))
                .map(this::setRandomSeller)
                .map(this::setRandomBuyer)
                .map(this::setRandomCategories)
                .collect(Collectors.toList());

        this.productRepository.saveAll(products);
    }


    private Product setRandomCategories(Product products) {
        long categoriesCount = this.categoryRepository.count();

        int count = new Random().nextInt((int) categoriesCount);

        Set<Category> categorySet = new HashSet<>();
        for (int i = 0; i < count; i++) {
            int randomId = new Random().nextInt((int) categoriesCount) + 1;

            Optional<Category> category = this.categoryRepository.findById(randomId);
            categorySet.add(category.get());
        }

        products.setCategories(categorySet);
        return products;
    }

    private Product setRandomBuyer(Product products) {
        if (products.getPrice().compareTo(BigDecimal.valueOf(944)) > 0){
            return products;
        }

        long usersCount = this.userRepository.count();

        int randomUserId = new Random().nextInt((int) usersCount) + 1;

        Optional<User> buyer = this.userRepository.findById(randomUserId);

        products.setBuyer(buyer.get());

        return products;
    }

    private Product setRandomSeller(Product products) {
        long usersCount = this.userRepository.count();

        int randomUserId = new Random().nextInt((int) usersCount) + 1;

        Optional<User> seller = this.userRepository.findById(randomUserId);

        products.setSeller(seller.get());

        return products;
    }
}
