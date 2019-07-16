package com.practice.web.controller;


import com.practice.web.model.Category;
import com.practice.web.model.Model;
import com.practice.web.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
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


    @GetMapping(value = "/categoryEdit/{id}")
    public ModelAndView displayEditCategoryForm(@PathVariable long id) {
        ModelAndView mv = new ModelAndView();
        Category category = categoryRepository.findCategoryById(id);
        mv.addObject("category", category);
        mv.setViewName("categoryEdit");
        return mv;
    }

    @PostMapping(value = "/categoryEdit/save")
    public ModelAndView saveEditedCategory(@ModelAttribute Category category, BindingResult result) {
        ModelAndView mv = new ModelAndView("redirect:/category");

        if (result.hasErrors()) {
            System.out.println(result.toString());
            return new ModelAndView("error");
        }
        categoryRepository.save(category);
        return mv;
    }
}
