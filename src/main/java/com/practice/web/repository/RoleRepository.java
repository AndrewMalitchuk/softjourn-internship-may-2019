package com.practice.web.repository;

import com.practice.web.model.Book;
import com.practice.web.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface RoleRepository extends JpaRepository<Role, Integer> {



}