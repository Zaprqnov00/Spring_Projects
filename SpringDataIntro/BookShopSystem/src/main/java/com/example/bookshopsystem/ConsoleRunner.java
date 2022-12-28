package com.example.bookshopsystem;

import com.example.bookshopsystem.entities.Author;
import com.example.bookshopsystem.entities.Book;
import com.example.bookshopsystem.entities.enums.AgeRestriction;
import com.example.bookshopsystem.entities.enums.EditionType;
import com.example.bookshopsystem.repositories.AuthorRepository;
import com.example.bookshopsystem.repositories.BookRepository;
import com.example.bookshopsystem.services.seed.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final SeedService seedService;

    @Autowired
    public ConsoleRunner(BookRepository bookRepository, AuthorRepository authorRepository, SeedService seedService) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.seedService = seedService;
    }

    @Override
    public void run(String... args) throws Exception {
        //Seeds ----->
        //this.seedService.seedAuthors();
        //this.seedService.seedCategories();
        //this.seedService.seedBooks();
        //this.seedService.seedAllData();

        //Queries_Spring_Data_Intro --->
        //this.getAllBooksAfterYear2000();
        //this.getAllAuthorsWithBooksReleaseDateBefore1990();
        //this.getAllAuthorsByCountTheirBooks();
        //this.getAllBooksByAuthorNames();

        //Queries_Spring_Data_Advanced ----->
        //this.getBooksTitleByAgeRestriction_01();
        //this.getGoldenBooks_02();
        //this.getBooksByPrice_03();
        //this.getBooksNotReleasedIn_04();
        //this.getBooksBeforeReleasedDate_05();
        //this.getAuthorsWhereEndWithLetter_06();
        //this.booksSearch_07();
        //this.getBooksByAuthorsLastNameEndingWith_08();
        //this.getCountBooks_09();
        //this.getTotalCopiesOfBooks_10();
        //this.getBookByTitle_11();
    }

    //Queries_Spring_Data_Intro --->
    private void getAllBooksAfterYear2000() {

        LocalDate releaseDate = LocalDate.of(2000, 1, 1);

        List<Book> books = this.bookRepository.findBooksByReleaseDateAfter(releaseDate);

        books.forEach(book -> System.out.println(book.getTitle()));
    }
    private void getAllAuthorsWithBooksReleaseDateBefore1990(){
        LocalDate releaseDate = LocalDate.of(1990,1,1);

        List<Author> authors = this.authorRepository.findDistinctByBooksReleaseDateBefore(releaseDate);

        authors.forEach(author -> System.out.printf("%s %s\n",
                author.getFirstName(),
                author.getLastName()));
    }
    private void getAllAuthorsByCountTheirBooks(){

        List<Author> authors = this.authorRepository.findAll();

        authors
                .stream()
                .sorted((l,r) -> r.getBooks().size() - l.getBooks().size())
                .forEach(author -> System.out.printf("%s %s -> %d\n",
                        author.getFirstName(),
                        author.getLastName(),
                        author.getBooks().size()));

    }
    private void getAllBooksByAuthorNames(){

        List<Book> books = this.bookRepository.findBooksByAuthorId(4);

        books
                .stream()
                .sorted(Comparator.comparing(Book::getReleaseDate).reversed())
                .sorted(Comparator.comparing(Book::getTitle))
                .forEach(book -> System.out.printf("%s %s -> %d\n",
                        book.getTitle(),
                        book.getReleaseDate(),
                        book.getCopies()));
    }


    ////Queries_Spring_Data_Advanced----->
    private void getBooksTitleByAgeRestriction_01(){
        Scanner scanner = new Scanner(System.in);
        String restriction = scanner.nextLine();
        AgeRestriction ageRestriction = AgeRestriction.valueOf(restriction.toUpperCase());

        List<Book> books = this.bookRepository.findBooksByAgeRestriction(ageRestriction);

        books.forEach(book -> System.out.println(book.getTitle()));

    }
    private void getGoldenBooks_02(){
        EditionType editionType = EditionType.GOLD;
        int copies = 5000;

        List<Book> books = this.bookRepository.findBooksByEditionTypeAndCopiesLessThan(editionType, copies);
        books.forEach(book -> System.out.println(book.getTitle()));
    }
    private void getBooksByPrice_03(){
        BigDecimal lowerPrice = new BigDecimal(5);
        BigDecimal higherPrice = new BigDecimal(40);

        List<Book> books = this.bookRepository.findBooksByPriceIsLessThanOrPriceIsGreaterThan(lowerPrice, higherPrice);

        books.forEach(book -> System.out.printf("%s - $%s\n",
                book.getTitle(),
                book.getPrice()));
    }
    private void getBooksNotReleasedIn_04(){

        LocalDate localDate2000 = LocalDate.of(2000,1,1);
        LocalDate localDate1998 = LocalDate.of(1998,1,1);
        List<Book> books = this.bookRepository.findBooksByReleaseDateNot(localDate2000);

        books.forEach(book -> System.out.println(book.getTitle()));
    }
    private void getBooksBeforeReleasedDate_05(){
        LocalDate firstLocalDate = LocalDate.parse("12-04-1992", DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        LocalDate secondLocalDate = LocalDate.parse("30-12-1989", DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        List<Book> books = this.bookRepository.findBooksByReleaseDateBefore(firstLocalDate);

        books.forEach(book -> System.out.printf("%s %s %s\n",
                book.getTitle(),
                book.getEditionType(),
                book.getPrice()));
    }
    private void getAuthorsWhereEndWithLetter_06(){
        Scanner scanner = new Scanner(System.in);
        String letter = scanner.nextLine();
        List<Author> authors = this.authorRepository.findAuthorsByFirstNameEndingWith(letter);

        authors.forEach(author -> System.out.printf("%s %s\n",
                author.getFirstName(),
                author.getLastName()));
    }
    private void booksSearch_07(){
        Scanner scanner = new Scanner(System.in);
        String letters = scanner.nextLine().toLowerCase();

        List<Book> books = this.bookRepository.findAll();

        books
                .stream()
                .filter(book -> book.getTitle().toLowerCase().contains(letters))
                .forEach(book -> System.out.println(book.getTitle()));

    }
    private void getBooksByAuthorsLastNameEndingWith_08(){
        Scanner scanner = new Scanner(System.in);
        String lastNameLetters = scanner.nextLine();

        List<Book> books = this.bookRepository.findAll();

        books
                .stream()
                .filter(book -> book.getAuthor().getLastName().startsWith(lastNameLetters))
                .forEach(book -> System.out.println(book.getTitle()));

    }
    private void getCountBooks_09(){
        Scanner scanner = new Scanner(System.in);
        int length = Integer.parseInt(scanner.nextLine());

        List<Book> books = this.bookRepository.findAll();

        int count = 0;
        for (Book book :books) {
            if (book.getTitle().length() == length){
                count++;
            }
        }

        System.out.printf("There are %d books with longer title than %d symbols\n", count, length);
    }
    private void getTotalCopiesOfBooks_10(){
        List<Book> books = this.bookRepository.findAll();

        books
                .stream()
                .sorted(Comparator.comparingInt(Book::getCopies).reversed())
                .limit(1)
                .forEach(book -> System.out.printf("%s %s - %d\n",
                        book.getAuthor().getFirstName(),
                        book.getAuthor().getLastName(),
                        book.getCopies()));
    }
    private void getBookByTitle_11(){
        Scanner scanner = new Scanner(System.in);
        String title = scanner.nextLine();

        List<Book> books = this.bookRepository.findBooksByTitleContains(title);

        books
                .stream()
                .limit(1)
                .forEach(book -> System.out.printf("%s %s %s %s\n",
                book.getTitle(),
                book.getEditionType(),
                book.getAgeRestriction(),
                book.getPrice()));
    }
}
