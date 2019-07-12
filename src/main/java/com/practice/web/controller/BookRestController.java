package com.practice.web.controller;

import com.practice.web.model.*;
import com.practice.web.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


@RestController
@RequestMapping(path = "/api/book")
@Validated
public class BookRestController {

    @Autowired
    private BookCategoryRepository bookCategoryRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserBookRepository userBookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping(path = "/", produces = "application/json")
    public Model index() {
        return new Model("book_test_success");
    }

    @GetMapping(path = "/datetime", produces = "application/json")
    public Model time() {
        return new Model(LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
    }

    @GetMapping(path = "/all", produces = "application/json")
    public Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping(path = "/allCategories", produces = "application/json")
    public Iterable<Category> allCategories() {
        return categoryRepository.findAll();
    }

    @GetMapping(path = "/allBooksCategory", produces = "application/json")
    public Iterable<BookCategory> getAllBooksCategory() {
        return bookCategoryRepository.findAll();
    }

    @GetMapping(path = "/allUserBooks", produces = "application/json")
    public Iterable<UserBook> getAllUserBooks() {
        return userBookRepository.findAll();
    }

    @GetMapping(path = "/allBooksByCategory", produces = "application/json")
    public Iterable<Book> getAllBooksByCategory(@Min(3) @Param("category") String category) {
        return bookRepository.getBookByCategory(category);
    }

    @GetMapping(path = "/allBooksByUser", produces = "application/json")
    public Iterable<Book> getAllBooksByUser(@Min(1) @Param("id") String id) {
        return bookRepository.getBookByUser(id);
    }

    @GetMapping(path = "/bookByName", produces = "application/json")
    public Iterable<Book> getBookByName(@Min(3) @Param("name") String name) {
        return bookRepository.getBookByName(name);
    }

    @PostMapping(path = "/buyBook")
    public void buyBook(@Valid @RequestBody Book book, HttpServletRequest request) {
        System.out.println(book.getIdBook() + " " + request.getUserPrincipal().getName());
        Book certainBook = bookRepository.getOne(book.getIdBook());
        if (certainBook.getCountCopies() > 0) {
            certainBook.setCountCopies(certainBook.getCountCopies() - 1);
            bookRepository.save(certainBook);
            userBookRepository.insertNewValueUsingUserName(book.getIdBook(), request.getUserPrincipal().getName());
        }
    }

    @PostMapping(path="/addBook")
    public void addBook(@Valid @RequestBody Book book){
        bookRepository.save(book);
    }

}
