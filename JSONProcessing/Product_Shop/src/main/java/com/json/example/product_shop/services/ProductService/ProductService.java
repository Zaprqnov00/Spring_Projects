package com.json.example.product_shop.services.ProductService;

import com.json.example.product_shop.domain.dtos.category.CategoryStats;
import com.json.example.product_shop.domain.dtos.product.GetAllProductsInformation;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    List<CategoryStats> getCategoryStatistics();


    List<GetAllProductsInformation> getProductsInRange();
}
