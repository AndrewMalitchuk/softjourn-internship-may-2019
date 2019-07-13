package com.practice.web.repository;

import com.practice.web.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

//CrudRepository - perform CRUD operation for db
@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "SELECT * FROM user WHERE user.name LIKE CONCAT('%',?,'%') " +
            "AND user.surname LIKE CONCAT('%',?,'%') " +
            "AND user.email LIKE CONCAT('%',?,'%')", nativeQuery = true)
    Iterable<User> getUserByName(String name, String surname, String email);
}
