package com.json.example.product_shop.constants;

import java.nio.file.Path;

public enum Paths {
    ;
    public static final Path USER_JSON_PATH =
            Path.of("src","main", "resources", "json_files", "users.json");

    public static final Path CATEGORY_JSON_PATH =
            Path.of("src", "main", "resources", "json_files", "categories.json");

    public static final Path PRODUCT_JSON_PATH =
            Path.of("src", "main", "resources", "json_files", "products.json");
}
