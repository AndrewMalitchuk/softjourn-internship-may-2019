package com.practice.web.repository;


import com.practice.web.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface BookRepository extends JpaRepository<Book, Long> {

    public static final String SELECT_BY_CATEGORY=
            "select books.book.* from books.book \n" +
                    "inner join books.book_category on books.book.id_book=books.book_category.book_id\n" +
                    "inner join books.category on books.category.id_category=books.book_category.category_id\n" +
                    "where books.category.genre=?1";

    public static final String SELECT_BOOK_BY_USER=
            "SELECT \n" +
                    "    books.book.*\n" +
                    "FROM\n" +
                    "    books.book\n" +
                    "        INNER JOIN\n" +
                    "    books.user_books ON books.user_books.book_id = books.book.id_book\n" +
                    "        INNER JOIN\n" +
                    "    books.user ON books.user.id_user = books.user_books.user_id\n" +
                    "WHERE\n" +
                    "    books.user.id_user = ?1";

    public static final String SELECT_BOOK_BY_NAME=
            "SELECT \n" +
                    "    *\n" +
                    "FROM\n" +
                    "    books.book\n" +
                    "WHERE\n" +
                    "    books.book.name LIKE CONCAT('%',:query,'%')";

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = SELECT_BY_CATEGORY, nativeQuery = true)
    public Iterable<Book> getBookByCategory(String query);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = SELECT_BOOK_BY_USER, nativeQuery = true)
    public Iterable<Book> getBookByUser(String query);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = SELECT_BOOK_BY_NAME, nativeQuery = true)
    public Iterable<Book> getBookByName(String query);



}