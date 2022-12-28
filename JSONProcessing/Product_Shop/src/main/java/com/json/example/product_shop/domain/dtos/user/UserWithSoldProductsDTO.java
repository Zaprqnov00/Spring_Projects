package com.json.example.product_shop.domain.dtos.user;

import com.json.example.product_shop.domain.dtos.product.SoldProductDTO;

import java.util.List;

public class UserWithSoldProductsDTO {
    private String firstName;
    private String lastName;
    private List<SoldProductDTO> boughtProducts;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<SoldProductDTO> getBoughtProducts() {
        return boughtProducts;
    }

    public void setBoughtProducts(List<SoldProductDTO> boughtProducts) {
        this.boughtProducts = boughtProducts;
    }
}
