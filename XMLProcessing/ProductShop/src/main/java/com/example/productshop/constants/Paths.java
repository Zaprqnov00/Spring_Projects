package com.example.productshop.constants;

import java.nio.file.Path;

public enum Paths {
    ;
    public static final Path USER_XML_PATH =
            Path.of("src", "main", "resources", "files", "users.xml");

    public static final Path CATEGORY_XML_PATH =
            Path.of("src", "main", "resources", "files", "categories.xml");

    public static final Path PRODUCT_XML_PATH =
            Path.of("src", "main", "resources", "files", "products.xml");


}
