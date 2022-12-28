package com.example.bookshopsystem.services.seed;

import com.example.bookshopsystem.entities.Author;
import com.example.bookshopsystem.entities.Book;
import com.example.bookshopsystem.entities.Category;
import com.example.bookshopsystem.entities.enums.AgeRestriction;
import com.example.bookshopsystem.entities.enums.EditionType;
import com.example.bookshopsystem.repositories.AuthorRepository;
import com.example.bookshopsystem.repositories.BookRepository;
import com.example.bookshopsystem.repositories.CategoryRepository;
import com.example.bookshopsystem.services.author.AuthorService;
import com.example.bookshopsystem.services.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SeedServiceImpl implements SeedService {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BookRepository bookRepository;

    private static final String RESOURCE_PATH = "src/main/resources/files";
    private static final String AUTHOR_FILE = RESOURCE_PATH + "/authors.txt";
    private static final String CATEGORY_FILE = RESOURCE_PATH + "/categories.txt";
    private static final String BOOK_FILE = RESOURCE_PATH + "/books.txt";

    @Override
    public void seedAuthors() throws IOException {
        Files.readAllLines(Path.of(AUTHOR_FILE))
                .stream()
                .filter(lines -> !lines.isBlank())
                .map(authorParts -> authorParts.split(" "))
                .map(createAuthor -> new Author(createAuthor[0], createAuthor[1]))
                .forEach(authorRepository::save);

    }

    @Override
    public void seedCategories() throws IOException {
        Files.readAllLines(Path.of(CATEGORY_FILE))
                .stream()
                .filter(lines -> !lines.isBlank())
                .map(Category::new)
                .forEach(categoryRepository::save);
    }

    @Override
    public void seedBooks() throws IOException {
        Files.readAllLines(Path.of(BOOK_FILE))
                .stream()
                .filter(lines -> !lines.isBlank())
                .map(this::getFillBook)
                .forEach(bookRepository::save);
    }

    private Book getFillBook(String lines) {
        String[] bookParts = lines.split(" ");

        EditionType editionType = EditionType.values()[Integer.parseInt(bookParts[0])];
        LocalDate releaseDate = LocalDate.parse(bookParts[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
        int copies = Integer.parseInt(bookParts[2]);
        BigDecimal price = new BigDecimal(bookParts[3]);
        AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(bookParts[4])];
        String title = Arrays
                .stream(bookParts)
                .skip(5)
                .collect(Collectors.joining(" "));
        Author author = authorService.getRandomAuthor();
        Set<Category> categories = categoryService.getRandomCategories();

        return new Book(title, editionType, price, copies, releaseDate, ageRestriction, author, categories);
    }
}
