package com.practice.web.repository;

import com.practice.web.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Long> {

    String DELETE_USER_BY_ID="DELETE FROM books.user_role \n" +
            "WHERE\n" +
            "    books.user_role.user_id = ?";

    Role findByRole(String role);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = DELETE_USER_BY_ID, nativeQuery = true)
    void deleteById(Long id);



}