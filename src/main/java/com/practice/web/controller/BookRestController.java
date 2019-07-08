package com.practice.web.controller;

import com.practice.web.model.*;
import com.practice.web.repository.BookCategoryRepository;
import com.practice.web.repository.BookRepository;
import com.practice.web.repository.CategoryRepository;
import com.practice.web.repository.UserBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@RestController
@RequestMapping(path = "/api/book")
public class BookRestController
{

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookCategoryRepository bookCategoryRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserBookRepository userBookRepository;

    @GetMapping(path="/", produces = "application/json")
    public Model index(){
        return  new Model("book_test_success");
    }

    @GetMapping(path = "/datetime",produces = "application/json")
    public  Model time(){
        return new Model(LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
    }

    @GetMapping(path = "/all",produces = "application/json")
    public Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }


    @GetMapping(path = "/allCategories",produces = "application/json")
    public Iterable<Category> allCategories() {
        return categoryRepository.findAll();
    }

    @GetMapping(path = "/allBooksCategory",produces = "application/json")
    public Iterable<BookCategory> getAllBooksCategory() {
        return bookCategoryRepository.findAll();
    }

    @GetMapping(path = "/allUserBooks", produces = "application/json")
    public Iterable<UserBook> getAllUserBooks(){
        return userBookRepository.findAll();

    }



}
