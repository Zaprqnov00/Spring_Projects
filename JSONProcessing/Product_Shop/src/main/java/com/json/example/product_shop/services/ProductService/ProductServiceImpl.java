package com.json.example.product_shop.services.ProductService;

import com.json.example.product_shop.domain.dtos.category.CategoryStats;
import com.json.example.product_shop.domain.dtos.product.GetAllProductsInformation;
import com.json.example.product_shop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<CategoryStats> getCategoryStatistics() {

        return this.productRepository.getCategoryStats();
    }

    @Override
    public List<GetAllProductsInformation> getProductsInRange() {

        return this.productRepository.getProductsByPriceBetweenAndBuyerIsNullOrderByPriceAsc(
                BigDecimal.valueOf(500),
                BigDecimal.valueOf(1000));
    }
}
