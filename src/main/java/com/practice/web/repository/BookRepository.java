package com.practice.web.repository;

import com.practice.web.model.Book;
import org.springframework.data.repository.CrudRepository;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

//CrudRepository - perform CRUD operation for db
public interface BookRepository extends CrudRepository<Book, Integer> {

}