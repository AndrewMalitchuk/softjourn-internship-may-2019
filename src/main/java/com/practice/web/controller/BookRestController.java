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
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
    public Iterable<Book> getAllBooksByCategory(@Size(min = 3, max = 50) @Param("category") String category) {
        return bookRepository.getBookByCategory(category);
    }

    @GetMapping(path = "/allBooksByUser", produces = "application/json")
    public Iterable<Book> getAllBooksByUser(@Min(1) @Param("id") String id) {
        return bookRepository.getBookByUser(id);
    }

    @GetMapping(path = "/bookByName", produces = "application/json")
    public Iterable<Book> getBookByName(@Size(min = 1, max = 50) @Param("name") String name) {
        return bookRepository.getBookByName(name);
    }

    //IMHO, PUT is better neither POST in this case
    /*
    @PostMapping(path = "/buyBook")
    public void buyBook(@RequestBody Book book, HttpServletRequest request) {
        Book certainBook = bookRepository.getOne(book.getIdBook());
        if (certainBook.getCountCopies() > 0) {
            certainBook.setCountCopies(certainBook.getCountCopies() - 1);
            bookRepository.save(certainBook);
            userBookRepository.insertNewValueUsingUserName(book.getIdBook(), request.getUserPrincipal().getName());
        }
    }
    */

    @PutMapping(path = "/buyBook/{id}")
    public void buyBook(@PathVariable Long id, HttpServletRequest request) {
        Book certainBook = bookRepository.getOne(id);
        if (certainBook.getCountCopies() > 0) {
            certainBook.setCountCopies(certainBook.getCountCopies() - 1);
            bookRepository.save(certainBook);
            userBookRepository.insertNewValueUsingUserName(id, request.getUserPrincipal().getName());
        }
    }

    @PostMapping(path = "/addBook")
    public void addBook(@Valid @RequestBody Book book) {
        bookRepository.save(book);
    }

    @DeleteMapping(path="/deleteBook/{id}")
    public void deleteBookById(@PathVariable Long id){
        bookRepository.deleteById(id);
    }

    //
    //TODO: add validations
    @PutMapping(path="/performBuying/{id}")
    public void performBuying(@PathVariable Long id, HttpServletRequest request){
        @SuppressWarnings("unchecked")
        List<Long> books = (List<Long>) request.getSession().getAttribute("BOOK_ID");
        if (books == null) {
            books = new ArrayList<>();
            request.getSession().setAttribute("BOOK_ID", books);
        }
        books.add(id);
        request.getSession().setAttribute("BOOK_ID", books);
    }

    @PutMapping(path="/deleteFromCart/{id}")
    public void deleteFromCartById(@PathVariable Long id, HttpServletRequest request){
        @SuppressWarnings("unchecked")
        List<Long> books = (List<Long>) request.getSession().getAttribute("BOOK_ID");
        if (books == null) {
            books = new ArrayList<>();
            request.getSession().setAttribute("BOOK_ID", books);
        }
        books.remove(id);
        request.getSession().setAttribute("BOOK_ID", books);
    }

    @PutMapping(path="/deleteFromCart/all")
    public void deleteAllFromCart(HttpServletRequest request){
        @SuppressWarnings("unchecked")
        List<Long> books = (List<Long>) request.getSession().getAttribute("BOOK_ID");
        if (books == null) {
            books = new ArrayList<>();
            request.getSession().setAttribute("BOOK_ID", books);
        }
        books.clear();
        request.getSession().setAttribute("BOOK_ID", books);
    }

    @PutMapping(path = "/buyBook/all")
    public void buyAllBook(HttpServletRequest request) {
        @SuppressWarnings("unchecked")
        List<Long> books = (List<Long>) request.getSession().getAttribute("BOOK_ID");
        if (books == null) {
            books = new ArrayList<>();
            request.getSession().setAttribute("BOOK_ID", books);
        }
        for(Long i:books){
            Book certainBook = bookRepository.getOne(i);
            if (certainBook.getCountCopies() > 0) {
                certainBook.setCountCopies(certainBook.getCountCopies() - 1);
                bookRepository.save(certainBook);
                userBookRepository.insertNewValueUsingUserName(i, request.getUserPrincipal().getName());
            }
        }
        request.getSession().setAttribute("BOOK_ID", books);
    }

    @GetMapping(path="getBook/{id}")
    @ResponseBody
    public Optional<Book> getBookById(@PathVariable Long id){
        return bookRepository.findById(id);
    }

    @GetMapping(path="getBookFromSession")
    @ResponseBody
    public Iterable<Book> getBookFromSesion(HttpServletRequest request){
        @SuppressWarnings("unchecked")
        List<Long> books = (List<Long>) request.getSession().getAttribute("BOOK_ID");
        if (books == null) {
            books = new ArrayList<>();
            request.getSession().setAttribute("BOOK_ID", books);
        }
        List<Book> list=new ArrayList<Book>();
        for(Long i:books){
            Book certainBook = bookRepository.getOne(i);
            if (certainBook.getCountCopies() > 0) {
                list.add(certainBook);
            }
        }
        request.getSession().setAttribute("BOOK_ID", books);
        Iterable<Book> i=list;
        return i;
    }

}
