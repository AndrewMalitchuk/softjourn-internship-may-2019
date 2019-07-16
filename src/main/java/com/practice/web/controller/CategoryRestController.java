package com.practice.web.controller;


import com.practice.web.model.Category;
import com.practice.web.model.Model;
import com.practice.web.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping(path = "/api/category")
@Validated
public class CategoryRestController {
    @Autowired
    CategoryRepository categoryRepository;

    @PostMapping(path = "/addCategory")
    public void addBook(@Valid @RequestBody Category category) {
        categoryRepository.save(category);
    }
}
