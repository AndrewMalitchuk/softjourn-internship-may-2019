package com.practice.web.repository;

import com.practice.web.model.Book;
import com.practice.web.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;


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

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = SELECT_USER_BY_ROLE, nativeQuery = true)
    public Iterable<User> getUserByRole(String query);



}
