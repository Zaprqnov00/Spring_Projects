package com.example.productshop.services;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public interface SeedService {

    void seedUsers() throws JAXBException, FileNotFoundException;

    void seedProducts() throws JAXBException, FileNotFoundException;

    void seedCategories() throws JAXBException, FileNotFoundException;

    default void seedAllData() throws JAXBException, FileNotFoundException {
        seedProducts();
        seedUsers();
        seedCategories();
    }

}
