package com.practice.web.repository;

import com.practice.web.model.Category;
import com.practice.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Transactional
    @Query(value = "SELECT * FROM category WHERE id_category=?", nativeQuery = true)
    Category findCategoryById(Long id);
}