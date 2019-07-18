package com.practice.web.repository;

import com.practice.web.model.Role;
import com.practice.web.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;


@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, Integer> {

    String SELECT_USER_BY_ROLE =
            "SELECT \n" +
                    "    books.user.*\n" +
                    "FROM\n" +
                    "    books.user\n" +
                    "        INNER JOIN\n" +
                    "    books.role ON books.user.role_id_role = books.role.id_role\n" +
                    "WHERE\n" +
                    "    books.role.type_user = ?1";

    //TODO: change books.user.name to books.user.nickname
    String SELECT_USER_BY_EMAIL =
            "SELECT \n" +
                    "    books.user.*\n" +
                    "FROM\n" +
                    "    books.user\n" +
                    "WHERE\n" +
                    "    books.user.email = ?1;";

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = SELECT_USER_BY_ROLE, nativeQuery = true)
    Iterable<User> getUserByRole(String query);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = SELECT_USER_BY_EMAIL, nativeQuery = true)
    Optional<User> getUserByEmail(String query);
    User findByEmail(String email);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "SELECT * FROM user WHERE user.name LIKE CONCAT('%',?,'%') " +
            "AND user.surname LIKE CONCAT('%',?,'%') " +
            "AND user.email LIKE CONCAT('%',?,'%')", nativeQuery = true)
    Iterable<User> getUserByName(String name, String surname, String email);


    @Transactional
    @Modifying
    @Query(value = "UPDATE user SET enabled = CASE enabled WHEN 1 THEN 0 ELSE 1 END WHERE id_user =?", nativeQuery = true)
    void disableUserById_user(Integer id_user);


    @Transactional
    @Query(value = "SELECT * FROM user WHERE id_user=?", nativeQuery = true)
    User findUserById_user(Integer id_user);


//    @Transactional
//    @Modifying
//    @Query(value = "UPDATE user SET enabled = CASE enabled WHEN 1 THEN 0 ELSE 1 END WHERE id_user =?", nativeQuery = true)
//    void update(String name, String surname, String sex, String address, String phone, Date date_of_birth, String email, String password);
}
