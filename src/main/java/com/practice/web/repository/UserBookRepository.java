package com.practice.web.repository;

import com.practice.web.model.UserBook;
import org.springframework.data.repository.CrudRepository;

public interface UserBookRepository extends CrudRepository<UserBook, Integer> {

}