package com.practice.web.repository;

import com.practice.web.model.UserBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface UserBookRepository extends JpaRepository<UserBook, Long> {

    String INSERT_NEW_VALUE_USING_USER_NAME =
            "INSERT INTO books.user_books \n" +
                    "            (book_id, \n" +
                    "             user_id) \n" +
                    "VALUES      (?1, \n" +
                    "             (SELECT books.user.id_user \n" +
                    "              FROM   books.user \n" +
                    "              WHERE  books.user.email =?2)) ";

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = INSERT_NEW_VALUE_USING_USER_NAME, nativeQuery = true)
    void insertNewValueUsingEmail(Long bookId, String email);

}