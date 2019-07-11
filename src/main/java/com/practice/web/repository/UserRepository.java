package com.practice.web.repository;

import com.practice.web.model.Book;
import com.practice.web.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.Optional;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

//CrudRepository - perform CRUD operation for db
public interface UserRepository extends CrudRepository<User, Integer> {

    public static final String SELECT_USER_BY_ROLE =
            "SELECT \n" +
            "    books.user.*\n" +
            "FROM\n" +
            "    books.user\n" +
            "        INNER JOIN\n" +
            "    books.role ON books.user.role_id_role = books.role.id_role\n" +
            "WHERE\n" +
            "    books.role.type_user = ?1";

    //XXX: change books.user.name to books.user.nickname
    public static final String SELECT_USER_BY_NICKNAME=
            "SELECT \n" +
                    "    books.user.*\n" +
                    "FROM\n" +
                    "    books.user\n" +
                    "WHERE\n" +
                    "    books.user.name = ?1;";

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = SELECT_USER_BY_ROLE, nativeQuery = true)
    public Iterable<User> getUserByRole(String query);


    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = SELECT_USER_BY_NICKNAME, nativeQuery = true)
    public Optional<User> getUserByNickname(String query);



}
