package com.practice.web.repository;

import com.practice.web.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

=======

public interface RoleRepository extends JpaRepository<Role, Integer> {
>>>>>>> frontend

@Repository("roleRepository")
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByRole(String role);
}