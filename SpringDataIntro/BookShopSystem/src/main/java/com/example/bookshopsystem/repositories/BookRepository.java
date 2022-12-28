package com.example.bookshopsystem.repositories;

import com.example.bookshopsystem.entities.Author;
import com.example.bookshopsystem.entities.Book;
import com.example.bookshopsystem.entities.enums.AgeRestriction;
import com.example.bookshopsystem.entities.enums.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findBooksByReleaseDateAfter(LocalDate releaseDate);

    List<Book> findBooksByAuthorId(int id);

    List<Book> findBooksByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findBooksByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);

    List<Book> findBooksByPriceIsLessThanOrPriceIsGreaterThan(BigDecimal lowerPrice, BigDecimal higherPrice);

    List<Book> findBooksByReleaseDateNot(LocalDate localDate);

    List<Book> findBooksByReleaseDateBefore(LocalDate localDate);

    List<Book> findBooksByTitleContains(String title);
}
