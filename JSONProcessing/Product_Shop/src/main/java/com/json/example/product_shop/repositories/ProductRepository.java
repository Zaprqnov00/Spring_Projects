package com.json.example.product_shop.repositories;

import com.json.example.product_shop.domain.dtos.category.CategoryStats;
import com.json.example.product_shop.domain.dtos.product.GetAllProductsInformation;
import com.json.example.product_shop.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT new com.json.example.product_shop.domain.dtos.category.CategoryStats(" +
            "c.name, COUNT(p), AVG(p.price), SUM(p.price))" +
            " FROM Product p" +
            " JOIN p.categories c" +
            " GROUP BY c")
    List<CategoryStats> getCategoryStats();

    List<GetAllProductsInformation> getProductsByPriceBetweenAndBuyerIsNullOrderByPriceAsc(BigDecimal lower, BigDecimal higher);
}
