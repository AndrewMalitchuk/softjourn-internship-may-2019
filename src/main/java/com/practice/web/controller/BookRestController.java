package com.practice.web.controller;

import com.practice.web.model.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@RestController
@RequestMapping(path = "/api/book")
public class BookRestController
{

    @GetMapping(path="/", produces = "application/json")
    public Model index(){
        return  new Model("book_test_success");
    }

    @GetMapping(path = "/datetime",produces = "application/json")
    public  Model time(){
        return new Model(LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
    }

}
