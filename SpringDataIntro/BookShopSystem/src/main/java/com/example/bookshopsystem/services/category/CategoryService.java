package com.example.bookshopsystem.services.category;

import com.example.bookshopsystem.entities.Category;

import java.util.Set;

public interface CategoryService {

    Set<Category> getRandomCategories();
}
