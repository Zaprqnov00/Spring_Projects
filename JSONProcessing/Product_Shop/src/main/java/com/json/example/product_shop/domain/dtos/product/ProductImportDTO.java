package com.json.example.product_shop.domain.dtos.product;

import java.math.BigDecimal;

public class ProductImportDTO {
    private String name;

    private BigDecimal price;

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
