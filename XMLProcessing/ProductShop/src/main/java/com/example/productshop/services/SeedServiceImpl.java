package com.example.productshop.services;

import com.example.productshop.domain.dto.category.CategoryImportDTO;
import com.example.productshop.domain.dto.product.ProductImportDTO;
import com.example.productshop.domain.dto.user.UserImportDTO;
import com.example.productshop.domain.entities.Category;
import com.example.productshop.domain.entities.Product;
import com.example.productshop.domain.entities.User;
import com.example.productshop.repositories.CategoryRepository;
import com.example.productshop.repositories.ProductRepository;
import com.example.productshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.productshop.constants.Paths.*;

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
    public void seedUsers() throws JAXBException, FileNotFoundException {
        JAXBContext context = JAXBContext.newInstance(UserImportDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        FileReader fileReader = new FileReader(USER_XML_PATH.toAbsolutePath().toString());
        UserImportDTO userImportDTO = (UserImportDTO) unmarshaller.unmarshal(fileReader);

        List<User> users = userImportDTO
                .getUsers()
                .stream()
                .map(userNameDTO ->
                                new User(userNameDTO.getFirstName()
                                , userNameDTO.getLastName()
                                , userNameDTO.getAge()))
                .collect(Collectors.toList());

        this.userRepository.saveAll(users);
    }

    @Override
    public void seedCategories() throws JAXBException, FileNotFoundException {
        JAXBContext context = JAXBContext.newInstance(CategoryImportDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        FileReader fileReader = new FileReader(CATEGORY_XML_PATH.toAbsolutePath().toString());
        CategoryImportDTO categoryImportDTO = (CategoryImportDTO) unmarshaller.unmarshal(fileReader);

        List<Category> categories =
                categoryImportDTO
                        .getCategories()
                        .stream()
                        .map(categoryNameDTO -> new Category(categoryNameDTO.getName()))
                        .collect(Collectors.toList());

        this.categoryRepository.saveAll(categories);
    }

    @Override
    public void seedProducts() throws JAXBException, FileNotFoundException {
        JAXBContext context = JAXBContext.newInstance(ProductImportDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        FileReader fileReader = new FileReader(PRODUCT_XML_PATH.toAbsolutePath().toString());
        ProductImportDTO productImportDTO = (ProductImportDTO) unmarshaller.unmarshal(fileReader);

        List<Product> products =
                productImportDTO
                        .getProducts()
                        .stream()
                        .map(productNameDTO -> new Product(productNameDTO.getName(), productNameDTO.getPrice()))
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

            Optional<Category> category = this.categoryRepository.findById((long) randomId);
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

        Optional<User> buyer = this.userRepository.findById((long) randomUserId);

        products.setBuyer(buyer.get());

        return products;
    }

    private Product setRandomSeller(Product products) {
        long usersCount = this.userRepository.count();

        int randomUserId = new Random().nextInt((int) usersCount) + 1;

        Optional<User> seller = this.userRepository.findById((long) randomUserId);

        products.setSeller(seller.get());

        return products;
    }
}
