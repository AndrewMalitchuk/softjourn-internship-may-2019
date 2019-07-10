package com.practice.web.repository;

import com.practice.web.model.Book;
import com.practice.web.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {



}