package com.json.example.product_shop.domain.dtos.product;

import com.json.example.product_shop.domain.entities.User;

import java.math.BigDecimal;
import java.util.List;

public class GetAllProductsInformation {
    private String name;
    private BigDecimal price;
    private List<UserGetName> seller;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<UserGetName> getSeller() {
        return seller;
    }

    public void setSeller(List<UserGetName> seller) {
        this.seller = seller;
    }
}
