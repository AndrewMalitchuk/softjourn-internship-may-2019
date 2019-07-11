package com.practice.web.repository;

import com.practice.web.model.User;
import com.practice.web.model.UserBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserBookRepository extends JpaRepository<UserBook, Integer> {


    public  static final  String INSERT_NEW_VALUE_USING_USER_NAME=
            "insert into books.user_books (book_id, user_id) values (?1,(select books.user.id_user from books.user where books.user.name=?2))";


    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = INSERT_NEW_VALUE_USING_USER_NAME, nativeQuery = true)
    public void insertNewValueUsingUserName(Long bookId, String username);




}