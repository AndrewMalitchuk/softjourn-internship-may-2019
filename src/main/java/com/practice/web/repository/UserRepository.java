package com.practice.web.repository;

import com.practice.web.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
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


    @Transactional
    @Modifying
//    @SQLDelete(sql="DELETE user, user_role FROM user INNER JOIN user_role ON" +
//            " user_role.id_user = user.id_user WHERE a.id_user =:id_user")
    @Query(value = "DELETE user, user_role FROM user INNER JOIN user_role ON" +
            " user_role.user_id = user.id_user WHERE user.id_user =?", nativeQuery = true)
    void deleteUserById_user(Integer id_user);
}
