package com.practice.web.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
@RequestMapping(path = "/book")
public class BookController {

    @RequestMapping(path="/")
    public String index(){
        return "/book";
    }

}