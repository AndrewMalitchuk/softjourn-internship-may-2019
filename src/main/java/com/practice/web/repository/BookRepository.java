package com.practice.web.repository;

import com.practice.web.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface BookRepository extends JpaRepository<Book, Long> {

    String SELECT_BY_CATEGORY =
            "SELECT books.book.* \n" +
                    "FROM   books.book \n" +
                    "       INNER JOIN books.book_category \n" +
                    "               ON books.book.id_book = books.book_category.book_id \n" +
                    "       INNER JOIN books.category \n" +
                    "               ON books.category.id_category = books.book_category.category_id \n" +
                    "WHERE  books.category.genre = ?";

    String SELECT_BOOK_BY_USER =
            "SELECT \n" +
                    "    books.book.*\n" +
                    "FROM\n" +
                    "    books.book\n" +
                    "        INNER JOIN\n" +
                    "    books.user_books ON books.user_books.book_id = books.book.id_book\n" +
                    "        INNER JOIN\n" +
                    "    books.user ON books.user.id_user = books.user_books.user_id\n" +
                    "WHERE\n" +
                    "    books.user.id_user = ?";

    String SELECT_BOOK_BY_NAME =
            "SELECT \n" +
                    "    *\n" +
                    "FROM\n" +
                    "    books.book\n" +
                    "WHERE\n" +
                    "    books.book.name LIKE CONCAT('%',?,'%')";

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = SELECT_BY_CATEGORY, nativeQuery = true)
    Iterable<Book> getBookByCategory(String query);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = SELECT_BOOK_BY_USER, nativeQuery = true)
    Iterable<Book> getBookByUser(String query);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = SELECT_BOOK_BY_NAME, nativeQuery = true)
    Iterable<Book> getBookByName(String query);


    @Transactional
    @Query(value = "SELECT * FROM book WHERE id_book = ?", nativeQuery = true)
    Book getBookById_book(Long id);
}